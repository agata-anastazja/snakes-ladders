(ns snake-and-ladders.game-stats)

(def initial-game-stats
  {:rolls-to-win-counter 0
   :climb-distances []
   :slide-distances []
   :highest-climb 0
   :highest-slide 0
   :longest-turn []
   :unlucky-rolls 0
   :lucky-rolls 0})

(def initial-simulation-stats
  {:rolls-to-win-counter {:minimum 0 :average 0 :maximum 0}
   :unlucky-rolls {:minimum 0 :average 0 :maximum 0}
   :lucky-rolls {:minimum 0 :average 0 :maximum 0}
   :climb-distances {:minimum 0 :average 0 :maximum 0}
   :slide-distances {:minimum 0 :average 0 :maximum 0}
   :highest-climb 0
   :highest-slide 0
   :longest-turn []})

(defn get-highest-climb [current-stats]
  (update-in current-stats [:highest-climb] (fn [_] (last (sort (:climb-distances current-stats))))))


(defn get-highest-slide [current-stats]
  (update-in current-stats [:highest-slide] (fn [_] (last (sort (:slide-distances current-stats))))))

(defn get-longest-turn [current-stats game]
  (update-in current-stats [:longest-turn]
             (fn [_] (->>
                      game
                      :turns
                      (reduce (fn [acc elem]
                                (if (or (> (count elem) (count acc)) (and (= (count elem) (count acc)) (> (reduce + elem) (reduce + acc))))
                                  elem
                                  acc)))))))

(defn produce-game-stats [game]
  (->
   initial-game-stats
   (assoc :rolls-to-win-counter (count (flatten (:turns game))))
   (assoc :climb-distances (mapv #(->>
                                   %
                                   reverse
                                   (reduce -)) (:climbs game)))
   (assoc :slide-distances (mapv #(->>
                                   %
                                   (reduce -)) (:slides game)))
   get-highest-climb
   get-highest-slide
   (get-longest-turn game)
   (assoc :lucky-rolls (:lucky-rolls game))
   (assoc :unlucky-rolls (:unlucky-rolls game))))


(defn get-property-vector [game-coll property]
  (map #(property %) game-coll))

(defn calculate-min-max-average [collection]
  (let [sorted-coll (sort collection)]
    {:minimum (first sorted-coll)
     :maximum (last sorted-coll)
     :average (/ (reduce + collection) (count collection))}))

(defn update-counter [game-stats-coll counter-name simulation-stats]
  (let [counters (get-property-vector game-stats-coll counter-name)
        stat (calculate-min-max-average counters)]
    (update-in simulation-stats [counter-name] (fn [_] stat))))

(defn update-unlucky-rolls [game-stats-coll simulation-stats]
  (update-counter game-stats-coll :unlucky-rolls simulation-stats))

(defn update-rolls-to-win [game-stats-coll simulation-stats]
  (update-counter game-stats-coll :rolls-to-win-counter simulation-stats))

(defn update-lucky-rolls [game-stats-coll simulation-stats]
  (update-counter game-stats-coll :lucky-rolls simulation-stats))

(defn update-climb-distances-and-highest-climb [game-stats-coll current-simulation-stats]
  (let [all-climbs (get-property-vector game-stats-coll :climb-distances)
        highest-climb (last (vec (sort (flatten all-climbs))))
        total-climbs-per-game (map #(reduce + %) all-climbs)
        stat (calculate-min-max-average total-climbs-per-game)]
    (-> (update-in current-simulation-stats [:climb-distances] (fn [_] stat))
        (update-in  [:highest-climb] (fn [_] highest-climb)))))

(defn update-slide-distances-and-highest-slide [game-stats-coll current-simulation-stats]
  (let [all-slides (get-property-vector game-stats-coll :slide-distances)
        highest-slide (last (vec (sort (flatten all-slides))))
        total-slides-per-game (map #(reduce + %) all-slides)
        stat (calculate-min-max-average total-slides-per-game)]
    (-> (update-in current-simulation-stats [:slide-distances] (fn [_] stat))
        (update-in  [:highest-slide] (fn [_] highest-slide)))))

(defn simulation-stats [game-stats-coll]
  (->>
   initial-simulation-stats
   (update-rolls-to-win game-stats-coll)
   (update-climb-distances-and-highest-climb game-stats-coll)
   (update-slide-distances-and-highest-slide game-stats-coll)
   (update-unlucky-rolls game-stats-coll)
   (update-lucky-rolls game-stats-coll)))
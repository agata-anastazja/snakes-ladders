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

(defn tap [x]
  (prn x)
  x)

(defn get-highest-climb [current-stats]
  (update-in current-stats [:highest-climb] (fn [_] (last (sort (:climb-distances current-stats))))))


(defn get-highest-slide [current-stats]
  (update-in current-stats [:highest-slide] (fn [_] (last (sort (:slide-distances current-stats))))))

(defn produce-game-stats [game]

  (->
   initial-game-stats
   (assoc :rolls-to-win-counter (count (:turns game)))
   (assoc :climb-distances (mapv #(->>
                                   %
                                   reverse
                                   (reduce -)) (:climbs game)))
   (assoc :slide-distances (mapv #(->>
                                   %
                                   (reduce -)) (:slides game)))
   get-highest-climb
   get-highest-slide))
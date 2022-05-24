(ns snake-and-ladders.main
  (:require [snake-and-ladders.core :as core]
            [snake-and-ladders.game-stats :as game-stats]))

(defn tap [x]
  (prn "!!! " x)
  x)

(def initial-stats
  {:rolls-to-win-counter {:minimum 0 :average 0 :maximum 0}
   :climb-distances {:minimum 0 :average 0 :maximum 0}
   :slide-distances {:minimum 0 :average 0 :maximum 0}
   :highest-climb 0
   :highest-slide 0
   :longest-turn []
   :unlucky-rolls {:minimum 0 :average 0 :maximum 0}
   :lucky-rolls {:minimum 0 :average 0 :maximum 0}})

(defn find-min-max-average [coll]
  (let [sorted-coll (sort coll)
        minimum (first sorted-coll)
        maximum (last sorted-coll)
        average (/ (reduce + coll) (count coll))]
    {:minimum minimum
     :maximum maximum
     :average average}))

(defn update-rolls-to-win [state games]
  (let [rolls-to-win-accross-all-games (map :rolls-to-win-counter games)
        stats (find-min-max-average rolls-to-win-accross-all-games)]
    (update-in state [:rolls-to-win-counter] (fn [_] stats))))

(defn update-climb-distances [state games]
  (let [climbs-accross-all-games  (map #(->>
                                         %
                                         :climb-distances
                                         (reduce +)) games)
        stats (find-min-max-average climbs-accross-all-games)]
    (update-in state [:climb-distances] (fn [_] stats))))

(defn update-slide-distances [state games]
  (let [slides-accross-all-games (map #(->>
                                        %
                                        :slide-distances
                                        (reduce +)) games)
        stats (find-min-max-average slides-accross-all-games)]
    (update-in state [:slide-distances] (fn [_] stats))))

(defn play-and-collect-stats [board rolls]
  (-> (core/play board rolls)
      (game-stats/produce-game-stats)))

(defn update-highest-climb [state games]
  (let [climbs (mapv #(->>
                       %
                       :highest-climb) games)
        highest-climb (last (vec (sort climbs)))]
    (update-in state [:highest-climb] (fn [_] highest-climb))))

(defn main [board rolls]
  (let [games (mapv #(play-and-collect-stats board %) rolls)]
    (->
     initial-stats
     (update-rolls-to-win games)
     (update-climb-distances games)
     (update-slide-distances games)

     (update-highest-climb games))))
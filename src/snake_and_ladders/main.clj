(ns snake-and-ladders.main
  (:require [snake-and-ladders.core :as core]
            [snake-and-ladders.game-stats :as game-stats]))

(def initial-stats
  {:rolls-to-win-counter {:minimum 0 :average 0 :maximum 0}
   :climb-distances {:minimum 0 :average 0 :maximum 0}
   :slide-distances {:minimum 0 :average 0 :maximum 0}
   :highest-climb 0
   :highest-slide 0
   :longest-turn []
   :unlucky-rolls {:minimum 0 :average 0 :maximum 0}
   :lucky-rolls {:minimum 0 :average 0 :maximum 0}})

(defn update-rolls-to-win [state games]
  (let [rolls-to-win-accross-all-games (map :rolls-to-win-counter games)       
        sorted-rolls (sort rolls-to-win-accross-all-games)
        minimum (first sorted-rolls)
        maximum (last sorted-rolls)
        average (/ (reduce + rolls-to-win-accross-all-games) (count rolls-to-win-accross-all-games))]
    (update-in state [:rolls-to-win-counter] assoc :minimum minimum :maximum maximum :average average)))

(defn update-climb-distances [state games]
  (let [climbs-accross-all-games (flatten (map :climb-distances games))
        sorted-climbs (sort climbs-accross-all-games)
        minimum (first sorted-climbs)
        maximum (last sorted-climbs)
        average (/ (reduce + climbs-accross-all-games) (count climbs-accross-all-games))]
    (update-in state [:climb-distances] assoc :minimum minimum :maximum maximum :average average)))

(defn play-and-collect-stats [board rolls]
  (-> (core/play board rolls)
      (game-stats/produce-game-stats)))

(defn main [board rolls]
  (let [games (mapv #(play-and-collect-stats board %) rolls)]
    (->
     initial-stats
     (update-rolls-to-win games)
     (update-climb-distances games))))
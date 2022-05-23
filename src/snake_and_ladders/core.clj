(ns snake-and-ladders.core
  (:require [snake-and-ladders.move :as move]
            [snake-and-ladders.move-stats :as move-stats]))

(def initial-game-state
  {:current-position 0
   :turns []
   :lucky-rolls 0
   :unlucky-rolls 0
   :step-on-ladder false
   :step-on-snake false
   :turns-in-strike-teritory 0
   :climbs []
   :slides []})

(defn reset-state [state]
  (assoc state  :step-on-snake false :step-on-ladder false))

(defn play-turn-and-collect-stats [state board roll]
  (-> state
      (move/make-move board roll)
      (move-stats/update-if-next-roll-can-be-strike (:current-position state))
      (move-stats/count-if-lucky-roll board)
      move-stats/count-if-unlucky-roll
      reset-state))

(defn play [board rolls]
  (reduce (fn [current-state roll]
            (if
             (= (:current-position current-state) 99)
              (reduced current-state)
              (play-turn-and-collect-stats current-state board roll)))
          initial-game-state
          rolls))


(ns snake-and-ladders.core
  (:require [snake-and-ladders.move :as move]))

(def initial-game-state
  {:current-position 0
   :turns []
   :lucky-rolls 0
   :step-on-ladder false
   :step-on-snake false
   :unlucky-rolls 0
   :climbs []
   :slides []})

(defn play [board rolls]
  (reduce (fn [current-state roll] (if
                                    (= (:current-position current-state) 99)
                                     (reduced current-state)
                                     (move/make-move current-state board roll)))
          initial-game-state
          rolls))

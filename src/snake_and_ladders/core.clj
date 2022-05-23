(ns snake-and-ladders.core)

(def initial-game-state 
  {:current-position 0
   :turns []
   :lucky-rolls 0
   :step-on-ladder false
   :step-on-snake false
   :unlucky-rolls 0
   :climbs []
   :slides []})
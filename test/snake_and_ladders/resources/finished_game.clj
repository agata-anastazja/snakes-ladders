(ns snake-and-ladders.resources.finished-game)

(def single-game
  {:turns [[1] [3] [6 2] [5] [4] [3] [4] [2] [2] [3] [3] [3] [6 3] [5] [5] [4] [3] [1] [4] [4] [5] [4] [1] [5] [2] [6 1] [5] [2] [2] [6 4] [6 2] [4] [3] [3] [1] [2] [2] [2] [3] [2] [3] [6 5] [5] [4] [2] [2] [2] [3] [2] [3] [6 5] [5] [4] [2] [2] [2] [3] [2] [3] [6 5] [5] [4] [2] [2] [2] [3] [2] [3] [6 6 6 4] [2] [2] [2] [3] [2] [3] [6 5] [5] [4] [2] [2] [2] [3] [2] [3] [6 5] [5] [4]], 
   :lucky-rolls 5, 
   :step-on-ladder false, 
   :slides [[4 2] [8 5]], 
   :step-on-snake false, 
   :turns-in-strike-teritory 1, 
   :unlucky-rolls 2, 
   :current-position 99, 
   :climbs [[30 40]]})
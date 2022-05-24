(ns snake-and-ladders.resources.board
  (:require
   [snake-and-ladders.board :refer [initialise-board]]))


(def board-with-snakes-and-ladders
  (initialise-board [[4 2]
                     [8 5]
                     [13 4]]
                    [[1 29]
                     [30 40]
                     [31 41]
                     [32 42]
                     [34 34]
                     [35 45]
                     [36 46]]))

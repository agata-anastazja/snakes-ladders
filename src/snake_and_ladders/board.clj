(ns snake-and-ladders.board)

(defn initialise-empty-board []
  (vec (repeat 100 {})))
(ns snake-and-ladders.board)

(defn initialise-empty-board []
  (vec (repeat 100 {})))

(defn apply-snake [board snake]
  (update board (first snake) (fn [_] {:snake snake})))

(defn apply-snakes [board snakes]
  (reduce (fn [acc snake] (apply-snake acc snake)) board snakes ))

(defn initialise-board [snakes ladders]
  (->
   (initialise-empty-board)
   (apply-snakes snakes)))
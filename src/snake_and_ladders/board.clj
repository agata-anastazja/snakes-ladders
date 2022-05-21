(ns snake-and-ladders.board)

(defn initialise-empty-board []
  (vec (repeat 100 {})))

(defn apply-snake [board snake]
  (update board (first snake) (fn [_] {:snake snake})))

(defn apply-ladder [board ladder]
    (update board (first ladder) (fn [_] {:ladder ladder})))

(defn apply-snakes [board snakes]
  (reduce (fn [acc snake] (apply-snake acc snake)) board snakes ))

(defn apply-ladders [board ladders]
  (reduce (fn [acc ladder] (apply-ladder acc ladder)) board ladders))

(defn initialise-board [snakes ladders]
  (->
   (initialise-empty-board)
   (apply-snakes snakes)
   (apply-ladders ladders)))
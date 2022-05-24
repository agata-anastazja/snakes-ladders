(ns snake-and-ladders.board
  (:require [snake-and-ladders.config :as config]))

(defn initialise-empty-board []
  (vec (repeat config/board-size {})))

(defn apply-snake [board snake]
  (update board (first snake) (fn [_] {:snake snake})))

(defn apply-ladder [board ladder]
  (update board (first ladder) (fn [_] {:ladder ladder})))

(defn apply-snakes [board snakes]
  (reduce (fn [acc snake] (apply-snake acc snake)) board snakes))

(defn apply-ladders [board ladders]
  (reduce (fn [acc ladder] (apply-ladder acc ladder)) board ladders))

(defn initialise-board [snakes ladders]
  (->
   (initialise-empty-board)
   (apply-snakes snakes)
   (apply-ladders ladders)))

(defn find-snakes [board]
  (reduce (fn [acc {:keys [snake]}]
            (if (not-empty snake)
              (conj acc snake)
              acc))
          []
          board))

(defn find-snake-entrypoints [board]
    (->>
     board
     find-snakes
     (map first)
     sort))
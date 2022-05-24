(ns snake-and-ladders.config
  (:require [environ.core :refer [env]]))

(def board-size
  (env :board-size 101))

(def winning-square-index
  (- board-size 1))

(def lucky-distance-from-snake-square 
  (env :distance-from-snake-square 2))

(def lucky-number-of-attempts-for-strike
  (env :lucky-number-of-attempts-for-strike 1))

(def dice-size
  (env :dice-size 6))

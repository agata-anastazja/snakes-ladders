(ns snake-and-ladders.board-test
  (:require [clojure.test :refer :all]
            [snake-and-ladders.board :refer :all]))

(deftest board-initialisation-test
  (testing "given the game is starting
            when you initialise the board
            you get an empty board with a 100 fields represented by empty dictionaries"
    (let [result (initialise-empty-board)]
      (is (= 100 (count result)))
      (is (every? #(= % {}) result))))
  (testing "given a list of snakes with one snake
            when you initialise the board
            you get a configured board with a snake"
    (let [snakes [[2 1]]
          ladders []
          result (initialise-board snakes ladders)]
      (is (= 100 (count result)))
      (is (= (nth result 2) {:snake [2 1]}))))
  (testing "given a list of snakes with 2 snakes
            when you initialise the board
            you get a configured board with 2 snakes"
    (let [snakes [[2 1] [4 3]]
          ladders []
          result (initialise-board snakes ladders)]
      (is (= 100 (count result)))
      (is (= (nth result 2) {:snake [2 1]}))
      (is (= (nth result 4) {:snake [4 3]}))))
  (testing "given a list of snakes with 1 ladder and 1 snake
            when you initialise the board
            you get a configured board with a ladder"
    (let [snakes [[4 3]]
          ladders [[2 5]]
          result (initialise-board snakes ladders)]
      (is (= 100 (count result)))
      (is (= (nth result 4) {:snake [4 3]}))
      (is (= (nth result 2) {:ladder [2 5]})))))



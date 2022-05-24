(ns snake-and-ladders.board-test
  (:require [clojure.test :refer :all]
            [snake-and-ladders.board :refer :all]))

(deftest board-initialisation-test
  (testing "given the game is starting
            when you initialise the board
            you get an empty board with a 101 squares represented by empty dictionaries, first one being the one where players start"
    (let [result (initialise-empty-board)]
      (is (= 101 (count result)))
      (is (every? #(= % {}) result))))
  (testing "given a list of snakes with one snake
            when you initialise the board
            you get a configured board with a snake"
    (let [snakes [[2 1]]
          ladders []
          result (initialise-board snakes ladders)]
      (is (= 101 (count result)))
      (is (= (nth result 2) {:snake [2 1]}))))
  (testing "given a list of snakes with 2 snakes
            when you initialise the board
            you get a configured board with 2 snakes"
    (let [snakes [[2 1] [4 3]]
          ladders []
          result (initialise-board snakes ladders)]
      (is (= 101 (count result)))
      (is (= (nth result 2) {:snake [2 1]}))
      (is (= (nth result 4) {:snake [4 3]}))))
  (testing "given a list of snakes with 1 ladder and 1 snake
            when you initialise the board
            you get a configured board with a ladder"
    (let [snakes [[4 3]]
          ladders [[2 5]]
          result (initialise-board snakes ladders)]
      (is (= (nth result 4) {:snake [4 3]}))
      (is (= (nth result 2) {:ladder [2 5]})))))

(deftest reading-board-test
  (testing "given a board with snakes
            you can find all the snakes"
    (let [snakes [[4 3] [7 5] [10 8]]
          ladders [[2 5]]
          board (initialise-board snakes ladders)
          result (find-snakes board)
          expected-result snakes]
      (is (= result expected-result))))
  (testing "given a board with snakes
            you can find all the snake entry points"
    (let [snakes [[4 3] [7 5] [10 8]]
          ladders [[2 5]]
          board (initialise-board snakes ladders)
          result (find-snake-entrypoints board)
          expected-result [4 7 10]]
      (is (= result expected-result)))))
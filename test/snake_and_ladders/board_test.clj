(ns snake-and-ladders.board-test
  (:require [clojure.test :refer :all]
            [snake-and-ladders.board :refer :all]))

(deftest board-initialisation-test
  (testing "given the game is starting
            when you initialise the board
            you get an empty board with a 100 fields represented by empty dictionaries"
    (let [result (initialise-empty-board)]
      (is (= 100 (count result)))
      (is (every? #(= % {}) result)))))



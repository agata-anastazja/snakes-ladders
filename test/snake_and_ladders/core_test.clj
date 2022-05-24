(ns snake-and-ladders.core-test
  (:require [clojure.test :refer :all]
            [snake-and-ladders.core :refer :all]
            [snake-and-ladders.test-helper :refer :all]
            [snake-and-ladders.board :refer :all]))

(deftest play-test
  (testing "given a board and a collection of rolls
            when you play the game until you reach square 99
            you play till you reach position 99 on the board"
    (let [random-rolls (random-rolls)
          board (initialise-empty-board)
          result (play board random-rolls)
          available-information (keys result)
          expected-information '(:turns :lucky-rolls :step-on-ladder :slides :step-on-snake :turns-in-strike-teritory :unlucky-rolls :current-position :climbs)]
      (is (= expected-information available-information))
      (is (= (:current-position result) 99))))
  (testing "given a board and rolls that land you on ladders
            when you play the game until you reach square 99
            you keep track of your slides"
    (let [random-rolls (random-rolls)
          board (initialise-board [[1 0] [2 1] [3 2] [4 3] [5 4]] [[10 99]])
          result (play board random-rolls)]
      (is (not= (:slides result) 0))))
  (testing "given a board and rolls that land you on ladders
            when you play the game until you reach square 99
            you keep track of your ladders"
    (let [random-rolls (random-rolls)
          board (initialise-board  [[10 1]] [[1 2] [2 3] [3 4] [4 5] [5 6]])
          result (play board random-rolls)]
      (is (not= (:slides result) 0))))
  (testing "given a board and rolls
            when you land on snakes 3 times
            you keep track of your slides and unlucky rolls"
    (let [rolls (rolls-starting-with [1 1 1 2])
          board (initialise-board   [[1 0]] [[10 99]])
          result (play board rolls)]
      (is (= (:slides result) [[1 0] [1 0] [1 0]]))
      (is (= (:unlucky-rolls result) 3))))
  (testing "given a board and rolls
            when you land on snakes 3 times and on ladder 1"
    (let [rolls  [1 1 1 2 1 1 1]
          board (initialise-board   [[1 0]] [[2 99]])
          result (play board rolls)]
      (is (= (:slides result) [[1 0] [1 0] [1 0]]))
      (is (= (:unlucky-rolls result) 3))
      (is (= (:climbs result) [[2 99]]))))
  (testing "given a board and rolls
            when you land on ladders 3 times
            you keep track of your climbs and lucky rolls"
    (let [rolls (no-6-rolls-starting-with [1 1 1 2])
          board (initialise-board [] [[1 2]
                                      [3 4]
                                      [5 6]])
          result (play board rolls)]
      (is (= (:climbs result) [[1 2]
                               [3 4]
                               [5 6]])))))
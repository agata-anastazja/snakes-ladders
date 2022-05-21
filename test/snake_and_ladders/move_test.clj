(ns snake-and-ladders.move-test
  (:require
   [clojure.test :refer :all]
   [snake-and-ladders.move :refer :all]
   [snake-and-ladders.board :refer [initialise-empty-board initialise-board]]
   [snake-and-ladders.core :refer [initial-game-state]]))

(deftest empty-board-roll-test
  (testing "Given an empty board and a player on tile 1
            when the roll is 3
            the player moves to tile 4"
    (let [board (initialise-empty-board)
          game-state initial-game-state
          expected-result 4]
      (is (= (:current-position (make-move game-state board 3)) expected-result))))
  (testing "Given an empty board and a player on tile 1
            when the roll is 3
            the player keeps a record of their turn"
    (let [board (initialise-empty-board)
          game-state initial-game-state
          expected-result [[3]]]
      (is (= (:turns (make-move game-state board 3)) expected-result))))
  (testing "Given an empty board and a player on tile 1
            when the rolls 2 and then 3
            the player keeps a record of both of their turns"
    (let [board (initialise-empty-board)
          game-state initial-game-state
          expected-result [[2] [3]]]
      (is (= (:turns (->
                      (make-move game-state board 2)
                      (make-move board 3))) expected-result))))
  (testing "Given an empty board and a player on tile 1
            when the rolls 6 and then 3
            the player keeps a record of this as a single turn"
    (let [board (initialise-empty-board)
          game-state initial-game-state
          expected-result [[6 3]]]
      (is (= (:turns (->
                      (make-move game-state board 6)
                      (make-move board 3))) expected-result))))) 

(deftest snakes-and-ladders-board-roll-test
  (testing "Given a board with snakes and ladders and a player on tile 1
            when the roll landing on a snake that takes them to tile 1
            the player stays on tile 1 and keeps record of their turn"
    (let [board (initialise-board [[4 1]] [[3 6]])
          game-state initial-game-state
          expected-position 1
          expected-turns [[3]]
          result  (make-move game-state board 3)]
      (is (= (:current-position result) expected-position))
      (is (= (:turns result) expected-turns)))))
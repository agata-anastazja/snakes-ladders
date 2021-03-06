(ns snake-and-ladders.move-test
  (:require
   [clojure.test :refer :all]
   [snake-and-ladders.move :refer :all]
   [snake-and-ladders.board :refer [initialise-empty-board initialise-board]]
   [snake-and-ladders.core :refer [initial-game-state]]))

(deftest empty-board-roll-test
  (testing "Given an empty board and a player on square 1
            when the roll is 3
            the player moves to square 4"
    (let [board (initialise-empty-board)
          game-state initial-game-state
          expected-result 3]
      (is (= (:current-position (make-move game-state board 3)) expected-result))))
  (testing "Given an empty board and a player on square 1
            when the roll is 3
            the player keeps a record of their turn"
    (let [board (initialise-empty-board)
          game-state initial-game-state
          expected-result [[3]]]
      (is (= (:turns (make-move game-state board 3)) expected-result))))
  (testing "Given an empty board and a player on square 1
            when the rolls 2 and then 3
            the player keeps a record of both of their turns"
    (let [board (initialise-empty-board)
          game-state initial-game-state
          expected-result [[2] [3]]]
      (is (= (:turns (->
                      (make-move game-state board 2)
                      (make-move board 3))) expected-result))))
  (testing "Given an empty board and a player on square 1
            when the rolls 6 and then 3
            the player keeps a record of this as a single turn"
    (let [board (initialise-empty-board)
          game-state initial-game-state
          expected-result [[6 3]]]
      (is (= (:turns (->
                      (make-move game-state board 6)
                      (make-move board 3))) expected-result))))
  (testing "Given an empty board and a player on square 97
            when the roll is 4
            the player moves stays on square 97, but keeps track of the roll"
    (let [board (initialise-empty-board)
          game-state  (update-in initial-game-state [:current-position] (fn [_] 97))
          result (make-move game-state board 4)
          expected-turns [[4]]
          expected-current-position 97]
      (is (= (:current-position result) expected-current-position))
      (is (= (:turns result) expected-turns)))))

(deftest snakes-and-ladders-board-roll-test
  (testing "Given a board with snakes
            when the roll landing on a snake that takes player to square 1
            the player stays on square 1 and keeps record of their turn
            as well as sets the stepped on snake flag to true"
    (let [board (initialise-board [[4 1]] [[3 6]])
          game-state initial-game-state
          expected-position 1
          expected-turns [[4]]
          result  (make-move game-state board 4)]
      (is (= (:current-position result) expected-position))
      (is (= (:turns result) expected-turns))
      (is (= (:step-on-snake result) true))))
  (testing "Given a board with snakes 
            when the roll landing on a snake that takes player to square 1
            we keep track of the slide"
    (let [board (initialise-board [[4 1]] [[3 6]])
          game-state initial-game-state
          expected-slides [[4 1]]
          result  (make-move game-state board 4)]
      (is (= (:slides result) expected-slides))))
  (testing "Given a board with ladders
            when the roll landing on a ladder that takes player to square 6
            the player moves to square 6 and keeps record of their turn
             as well as sets the stepped on ladder flag to true"
    (let [board (initialise-board [[4 1]] [[3 6]])
          game-state initial-game-state
          expected-position 6
          expected-turns [[3]]
          result  (make-move game-state board 3)]
      (is (= (:current-position result) expected-position))
      (is (= (:turns result) expected-turns))
      (is (= (:step-on-ladder result) true))))

  (testing "Given a board with snakes 
            when the roll landing on a ladder that takes player to square 6
            we keep track of the climb"
    (let [board (initialise-board [[4 1]] [[3 6]])
          game-state initial-game-state
          expected-climbs [[3 6]]
          result  (make-move game-state board 3)]
      (is (= (:climbs result) expected-climbs)))))



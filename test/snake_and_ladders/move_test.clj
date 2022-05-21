(ns snake-and-ladders.move-test
  (:require
   [clojure.test :refer :all]
   [snake-and-ladders.move :refer :all]
   [snake-and-ladders.board :refer [initialise-empty-board]]
    [snake-and-ladders.core :refer [initial-game-state]]
))

(deftest empty-board-roll-test
  (testing "Given an empty board and a player on tile 1
            when the roll is 3
            the player moves to tile 4" 
    (let [board (initialise-empty-board)
          game-state initial-game-state
          expected-result 4]
      (is (= (:current-position (make-move board game-state  3)) expected-result))))
  (testing "Given an empty board and a player on tile 1
            when the roll is 3
            the player keeps a record of their turn"
    (let [board (initialise-empty-board)
          game-state initial-game-state
          expected-result [[3]]]
      (is (= (:turns (make-move board game-state  3)) expected-result)))))


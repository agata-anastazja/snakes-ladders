(ns snake-and-ladders.move-stats-test
  (:require
   [clojure.test :refer :all]
   [snake-and-ladders.board :refer :all]
   [snake-and-ladders.core :refer :all]
   [snake-and-ladders.move-stats :refer :all]
   [snake-and-ladders.test-helper :refer [stepped-on-snake-game stepped-on-ladder-game game-with-current-position game-with-current-position-and-turns]]))

(deftest unlucky-roll-test
  (testing "Given a game
            when we step on a snake
            we keep track of the unlucky roll"
    (let [game-state (stepped-on-snake-game)
          result  (count-if-unlucky-roll game-state)]
      (is (= (:unlucky-rolls result) 1)))))

(deftest lucky-roll-test
  (testing "Given your previous position was square 97
            when you get to square 100 at the first atempt
            we keep track of the lucky roll"
    (let [board (initialise-board [[4 1]] [[10 16]])
          game-state (update-in (game-with-current-position 100) [:turns-in-strike-teritory] (fn [_] 1))
          result  (count-if-lucky-roll game-state board)]
      (is (= (:lucky-rolls result) 1))))
  (testing "Given you reach square 100
            when you got to 100 at the second atempt
            it does not count as lucky roll"
    (let [board (initialise-board [[4 1]] [[10 16]])
          game-state (update-in (game-with-current-position 100) [:turns-in-strike-teritory] (fn [_] 0))
          result  (count-if-lucky-roll game-state board)]
      (is (= (:lucky-rolls result) 0)))))
(testing "Given a game
          when we land on ladder
          we keep track of the lucky roll"
  (let [board (initialise-empty-board)
        game-state (stepped-on-ladder-game)
        result  (count-if-lucky-roll game-state board)]
    (is (= (:lucky-rolls result) 1))))
(testing "Given a game 
            when we land one square before the snake
            we keep track of the lucky roll"
  (let [board (initialise-board [[4 1]] [[5 6]])
        game-state (game-with-current-position 3)
        result  (count-if-lucky-roll game-state board)]
    (is (= (:lucky-rolls result) 1))))

(testing "Given a game
            when we land two squares after the snake
            we keep track of the lucky roll"
  (let [board (initialise-board [[4 1]] [[10 16]])
        game-state (game-with-current-position 6)
        result  (count-if-lucky-roll game-state board)]
    (is (= (:lucky-rolls result) 1))))


(deftest update-if-next-roll-can-be-strike-test
  (testing "Given a previous roll wasn't close enough to win in single roll
            when current position is at 93 
            next roll can be a lucky strike"
    (let [game-state (game-with-current-position-and-turns 94 [[92] [5] [4]])
          result (update-if-next-roll-can-be-strike game-state 92)]
      (is (= (:turns-in-strike-teritory result) 1))))
  (testing "Given a previous roll was close enough to win in single roll
            when current position is at 95
            next roll cannot be a lucky strike"
    (let [game-state (game-with-current-position-and-turns 96 [[92] [5] [4]])
          result (update-if-next-roll-can-be-strike game-state 95)]
      (is (= (:turns-in-strike-teritory result) 0)))))


(deftest lucky-strike-test
  (testing "Given we have reached winning spot in one turn
            from when we could have reached it
            it's a lucky strike"
    (let [game (update-in (game-with-current-position 100) [:turns-in-strike-teritory] (fn [_] 1))]
      (is (= (lucky-strike? game) true)))))
(ns snake-and-ladders.modified-move-counter-test
  (:require
   [clojure.test :refer :all]
   [snake-and-ladders.board :refer :all]
   [snake-and-ladders.core :refer :all]
   [snake-and-ladders.modified-move-counter :refer :all]
   [snake-and-ladders.test-helper :refer [stepped-on-snake-game stepped-on-ladder-game game-with-current-position game-with-current-position-and-turns]]))

(deftest unlucky-roll-test
  (testing "Given a game
            when we step on a snake
            we keep track of the unlucky roll"
    (let [game-state (stepped-on-snake-game)
          result  (count-if-unlucky-roll game-state)]
      (is (= (:unlucky-rolls result) 1)))))

(deftest lucky-roll-test
  (testing "Given a game
            when we land on ladder
            we keep track of the lucky roll"
    (let [board (initialise-empty-board)
          game-state (stepped-on-ladder-game)
          result  (count-if-lucky-roll game-state board)]
      (is (= (:lucky-rolls result) 1))))
  (testing "Given a game 
            when we land one tile before the snake
            we keep track of the lucky roll"
    (let [board (initialise-board [[4 1]] [[5 6]])
          game-state (game-with-current-position 3)
          result  (count-if-lucky-roll game-state board)]
      (is (= (:lucky-rolls result) 1))))

  (testing "Given a game
            when we land two tiles after the snake
            we keep track of the lucky roll"
    (let [board (initialise-board [[4 1]] [[10 16]])
          game-state (game-with-current-position 6)
          result  (count-if-lucky-roll game-state board)]
      (is (= (:lucky-rolls result) 1))))
  (testing "Given you reach position is 99
            when you got to 99 at the first atempt
            we keep track of the lucky roll"
    (let [board (initialise-board [[4 1]] [[10 16]])
          game-state (game-with-current-position-and-turns 99 [[91] [5] [3]])
          result  (count-if-lucky-roll game-state board)]
      (is (= (:lucky-rolls result) 1))))
  (testing "Given you reach position is 99
            when you got to 99 at the second atempt
            we don't keep track of the lucky roll"
    (let [board (initialise-board [[4 1]] [[10 16]])
          game-state (game-with-current-position-and-turns 99 [[93] [3] [3]])
          result  (count-if-lucky-roll game-state board)]
      (is (= (:lucky-rolls result) 0)))))

;; next test [[95] [6] [3]] rolls
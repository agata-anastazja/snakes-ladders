(ns snake-and-ladders.game-stats-test
  (:require
   [clojure.test :refer :all]
   [snake-and-ladders.game-stats :refer :all]
   [snake-and-ladders.test-helper :refer :all]))

(def example-finished-game
  {:turns [[1] [1] [1] [2]], :lucky-rolls 4, :step-on-ladder false, :slides [[1 0] [1 0] [1 0]], :step-on-snake false, :turns-in-strike-teritory 1, :unlucky-rolls 3, :current-position 99, :climbs [[2 99]]})

(deftest produce-game-stats-test
  (testing "Given a finished game state
            it produces game stats on number of rolls needed to win"
    (let [game example-finished-game
          result (produce-game-stats game)]
      (is (= (:rolls-to-win-counter result) 4))))
  (testing "Given a finished game state
            it produces game stats on distances on climbs"
    (let [game example-finished-game
          result (produce-game-stats game)]
      (is (= (:climb-distances result) [97]))))
  (testing "Given a finished game state
            it produces game stats  on distances on slides"
    (let [game example-finished-game
          result (produce-game-stats game)]
      (is (= (:slide-distances result) [1 1 1]))))
  (testing "Given a finished game state
            it produces game stats  on highest climb"
    (let [game example-finished-game
          result (produce-game-stats game)]
      (is (= (:highest-climb result) 97))))
   (testing "Given a finished game state
            it produces game stats  on highest slide"
     (let [game example-finished-game
           result (produce-game-stats game)]
       (is (= (:highest-slide result) 1))))
  (testing "Given a finished game state
            it produces game stats  on longest turn"
    (let [game example-finished-game
          result (produce-game-stats game)]
      (is (= (:longest-turn result) [2]))))
  (testing "Given a finished game state
            it produces game stats  on lucky rolls"
    (let [game example-finished-game
          result (produce-game-stats game)]
      (is (= (:lucky-rolls result) 4))))
  (testing "Given a finished game state
            it produces game stats  on unlucky rolls"
    (let [game example-finished-game
          result (produce-game-stats game)]
      (is (= (:unlucky-rolls result) 3)))))
(ns snake-and-ladders.game-stats-test
  (:require
   [clojure.test :refer :all]
   [snake-and-ladders.game-stats :refer :all]
   [snake-and-ladders.test-helper :refer :all]))

(deftest produce-game-stats-test
  (testing "Given a finished game state
            it produces game stats on number of rolls needed to win"
    (let [game {:turns [[1] [1] [1] [2]], :lucky-rolls 4, :step-on-ladder false, :slides [[1 0] [1 0] [1 0]], :step-on-snake false, :turns-in-strike-teritory 1, :unlucky-rolls 3, :current-position 99, :climbs [[2 99]]}
          result (produce-game-stats game)]
      (is (= (:rolls-to-win-counter result) 4)))))
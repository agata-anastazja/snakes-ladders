(ns snake-and-ladders.core-test
  (:require [clojure.test :refer :all]
            [snake-and-ladders.core :refer :all]
            [snake-and-ladders.test-helper :refer :all]
            [snake-and-ladders.board :refer :all]))

(deftest board-initialisation-test
  (testing "given a board and a collection of rolls
            when play the game until you reach field 99
            you get game state with information about the game"
    (let [random-rolls (take 100 (repeat roll))
          board (initialise-empty-board)
          result (play board random-rolls)
          available-information (keys result)
          expected-information '(:current-position :turns :lucky-rolls :step-on-ladder :step-on-snake :unlucky-rolls :climbs :slides)]
      (is (= expected-information available-information)))))


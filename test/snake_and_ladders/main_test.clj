(ns snake-and-ladders.main-test
  (:require
   [clojure.test :refer :all]
   [snake-and-ladders.main :refer :all]
   [snake-and-ladders.test-helper :refer :all]
   [snake-and-ladders.resources.rolls :refer :all]
   [snake-and-ladders.resources.board :refer [board-with-snakes-and-ladders]]
   [snake-and-ladders.resources.finished-game :refer :all]))


(deftest main-test
  (testing "Given board, you want to iterate once and a collection of rolls
       when run the game
       gives you game stats"
    (let [board board-with-snakes-and-ladders
          rolls rolls-for-game-with-slides-and-climbs
          result (main board [rolls])]
      (is (= (:rolls-to-win-counter result)
             {:minimum 40, :average 40, :maximum 40})))))

(deftest play-and-collect-stats-test
  (testing "Given board and rolls
            when you play the game
            you can collect stats from it"
    (let [result (play-and-collect-stats board-with-snakes-and-ladders rolls-for-game-with-slides-and-climbs)]
      (is (= result
             {:rolls-to-win-counter 40,
              :climb-distances [10],
              :slide-distances [2 3],
              :highest-climb 10,
              :highest-slide 3,
              :longest-turn [6 4],
              :unlucky-rolls 2,
              :lucky-rolls 6})))))
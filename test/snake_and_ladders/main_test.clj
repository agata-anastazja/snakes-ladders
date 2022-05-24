(ns snake-and-ladders.main-test
  (:require
   [clojure.test :refer :all]
   [snake-and-ladders.main :refer :all]
   [snake-and-ladders.test-helper :refer :all]
   [snake-and-ladders.resources.rolls :refer :all]
   [snake-and-ladders.resources.board :refer [board-with-snakes-and-ladders]]
   [snake-and-ladders.resources.finished-game :refer :all]))


(deftest main-test
  (testing "Given board and a collection of rolls for a single game
       when run the simulation
       it gives you game stats"
    (let [board board-with-snakes-and-ladders
          rolls rolls-for-game-with-slides-and-climbs
          result (main board [rolls])]
      (is (= (:rolls-to-win-counter result)
             {:minimum 40, :average 40, :maximum 40}))))
  (testing "Given board and a collection of rolls for a two games
       when run the game
       gives you game stats across outcomes from both those games"
    (let [board board-with-snakes-and-ladders
          rolls-when-stepping-on-modifiers rolls-for-game-with-slides-and-climbs
          result (main board [rolls-when-stepping-on-modifiers rolls-for-an-easy-game])]
      (is (= (:rolls-to-win-counter result)
            {:minimum 40, :average 111/2, :maximum 71})))))

(deftest play-and-collect-stats-test
  (testing "Given board and rolls
            when you play a game
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
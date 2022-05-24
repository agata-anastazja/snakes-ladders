(ns snake-and-ladders.main-test
  (:require
   [clojure.test :refer :all]
   [snake-and-ladders.main :refer :all]
   [snake-and-ladders.test-helper :refer :all]
   [snake-and-ladders.resources.rolls :refer :all]
   [snake-and-ladders.resources.board :refer [board-with-snakes-and-ladders]]
   [snake-and-ladders.resources.finished-game :refer :all]))

(deftest play-and-collect-stats-test
  (testing "Given board and some simplified rolls
            when you play a game
            you can collect stats from it"
    (let [result (play-and-collect-stats board-with-snakes-and-ladders rolls-for-an-easy-game)]
      (is (= result
             {:rolls-to-win-counter 32,
              :climb-distances [28 10],
              :slide-distances [],
              :highest-climb 28,
              :highest-slide nil,
              :longest-turn [6 6 6 6 6 6 1],
              :unlucky-rolls 0,
              :lucky-rolls 3}))))
  (testing "Given board and some randomised rolls
            when you play a game
            you can collect stats from it"
    (let [result (play-and-collect-stats board-with-snakes-and-ladders rolls-for-game-with-slides-and-climbs)]
      (is (= result
             {:rolls-to-win-counter 40,
              :climb-distances [0],
              :slide-distances [9 9 9],
              :highest-climb 0,
              :highest-slide 9,
              :longest-turn [6 4],
              :unlucky-rolls 3,
              :lucky-rolls 9})))))

(deftest main-rolls-to-win-counter-test
  (testing "Given board and a collection of rolls for a single game
       when run the simulation
       it gives you game stats for rolls-to-win information"
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
            {:minimum 32, :average 36, :maximum 40})))))

(deftest main-climb-distances-test
  (testing "Given board and a collection of rolls for a single game
       when run the simulation
       it gives you game stats for climb distances information"
    (let [board board-with-snakes-and-ladders
          rolls rolls-for-game-with-slides-and-climbs
          result (main board [rolls])]
      (is (= (:climb-distances result)
             {:minimum 0, :average 0, :maximum 0}))))
  (testing "Given board and a collection of rolls for a two games
       when run the game
       gives you game stats across outcomes from both those games"
    (let [board board-with-snakes-and-ladders
          rolls-when-stepping-on-modifiers rolls-for-game-with-slides-and-climbs
          result (main board [rolls-when-stepping-on-modifiers rolls-for-an-easy-game])]
      (is (= (:climb-distances result)
             {:minimum 0, :average 38/3, :maximum 28})))))

(deftest main-slide-distances-test
  (testing "Given board and a collection of rolls for a single game
       when run the simulation
       it gives you game stats for climb distances information"
    (let [board board-with-snakes-and-ladders
          rolls rolls-for-game-with-slides-and-climbs
          result (main board [rolls])]
      (is (= (:slide-distances result)
              {:minimum 9, :average 9, :maximum 9}))))
  (testing "Given board and a collection of rolls for a two games
       when run the game
       gives you game stats across outcomes from both those games"
    (let [board board-with-snakes-and-ladders
          rolls-when-stepping-on-modifiers rolls-for-game-with-slides-and-climbs
          result (main board [rolls-when-stepping-on-modifiers rolls-for-an-easy-game])]
      (is (= (:slide-distances result)
              {:minimum 9, :average 9, :maximum 9})))))



(ns snake-and-ladders.main-test
  (:require
   [clojure.test :refer :all]
   [snake-and-ladders.main :refer :all]
   [snake-and-ladders.test-helper :refer :all]
   [snake-and-ladders.resources.rolls :refer :all]
   [snake-and-ladders.resources.board :refer [board-with-snakes-and-ladders]]
   [snake-and-ladders.resources.finished-game :refer :all]))

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
             {:minimum 0, :maximum 38, :average 19})))))

(deftest main-slide-distances-test
  (testing "Given board and a collection of rolls for a single game
       when run the simulation
       it gives you game stats for climb distances information"
    (let [board board-with-snakes-and-ladders
          rolls rolls-for-game-with-slides-and-climbs
          result (main board [rolls])]
      (is (= (:slide-distances result)
              {:minimum 27, :average 27, :maximum 27}))))
  (testing "Given board and a collection of rolls for a two games
       when run the game
       gives you game stats across outcomes from both those games"
    (let [board board-with-snakes-and-ladders
          rolls-when-stepping-on-modifiers rolls-for-game-with-slides-and-climbs
          result (main board [rolls-when-stepping-on-modifiers rolls-for-an-easy-game])]
      (is (= (:slide-distances result)
              {:minimum 0, :average 27/2, :maximum 27})))))

(deftest main-highest-climb-test
  (testing "Given board and a collection of rolls for a single game
       when run the simulation
       it gives you game stats for climb distances information"
    (let [board board-with-snakes-and-ladders
          rolls rolls-for-game-with-slides-and-climbs
          result (main board [rolls])]
      (is (= (:highest-climb result) 0))))
  (testing "Given board and a collection of rolls for a two games
       when run the game
       gives you game stats across outcomes from both those games"
    (let [board board-with-snakes-and-ladders
          rolls-when-stepping-on-modifiers rolls-for-game-with-slides-and-climbs
          result (main board [rolls-when-stepping-on-modifiers rolls-for-an-easy-game])]
      (is (= (:highest-climb result) 28)))))

(deftest main-slide-climb-test
  (testing "Given board and a collection of rolls for a single game
       when run the simulation
       it gives you game stats for climb distances information"
    (let [board board-with-snakes-and-ladders
          rolls rolls-for-game-with-slides-and-climbs
          result (main board [rolls])]
      (is (= (:highest-slide result) 9))))
  (testing "Given board and a collection of rolls for a two games
       when run the game
       gives you game stats across outcomes from both those games"
    (let [board board-with-snakes-and-ladders
          rolls-when-stepping-on-modifiers rolls-for-game-with-slides-and-climbs
          result (main board [rolls-when-stepping-on-modifiers rolls-for-an-easy-game])]
      (is (= (:highest-slide result) 9)))))



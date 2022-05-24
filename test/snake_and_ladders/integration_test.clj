(ns snake-and-ladders.integration-test
  (:require
   [clojure.test :refer :all]
   [snake-and-ladders.main :refer :all]
   [snake-and-ladders.core :refer :all]
   [snake-and-ladders.test-helper :refer :all]
   [snake-and-ladders.resources.rolls :refer :all]))


;; (deftest main-test
;;   (testing "Given board, you want to iterate once and a collection of rolls
;;        when run the game
;;        gives you game stats"
;;     (let [board board-with-snakes-and-ladders
;;           rolls rolls-for-first-game
;;           result (main board 1 rolls)]
;;       (is (= (:rolls-to-win-counter result)
;;              {:minimum 0 :average 0 :maximum 0})))))


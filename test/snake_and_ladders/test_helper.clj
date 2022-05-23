(ns snake-and-ladders.test-helper
  (:require [snake-and-ladders.core :refer [initial-game-state]]))

(defn stepped-on-snake-game []
  (->
   initial-game-state
   (update-in [:step-on-snake] (fn[_] true))))

(defn stepped-on-ladder-game []
  (->
   initial-game-state
   (update-in [:step-on-ladder] (fn [_] true))))

(defn game-with-current-position [position]
  (update-in initial-game-state [:current-position] (fn [_] position)))

(defn game-with-current-position-and-turns [position turns]
  (->
   position
   game-with-current-position
   (update-in [:turns] (fn [_] turns))))

(ns snake-and-ladders.test-helper
  (:require [snake-and-ladders.core :refer [initial-game-state]]))

(defn stepped-on-snake-game []
  (->
   initial-game-state
   (update-in [:step-on-snake] (fn [_] true))))

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

(defn roll
  ([]
   (+ (rand-int 6) 1))
  ([limit]
   (+ (rand-int limit) 1)))

(defn random-rolls ([]
                    (doall  (repeatedly 100 roll)))
  ([limit]
   (doall  (repeatedly 100 #(roll limit)))))

(defn rolls-starting-with [starting-rolls]
  (vec (flatten (conj starting-rolls (random-rolls)))))

(defn no-6-rolls-starting-with [starting-rolls]
  (vec (flatten (conj starting-rolls (random-rolls 5)))))


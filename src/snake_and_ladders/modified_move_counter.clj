(ns snake-and-ladders.modified-move-counter
  (:require [snake-and-ladders.board :refer [find-snake-entrypoints]]))

(defn distance-from-nearest-snakes-two-or-less [tile snake-entry-points]
  (if (not-empty snake-entry-points)
    (let [snake-after-selected-tile (first (filter (fn [x] (> x tile)) snake-entry-points))
          snake-before-selected-tile  (first (filter (fn [x] (< x tile)) snake-entry-points))]
      (or (and snake-after-selected-tile (<= (- snake-after-selected-tile tile) 2))
          (and snake-before-selected-tile (<= (- tile snake-before-selected-tile) 2))))
    false))

(defn miss-snake? [board current-position]
  (->>
   board
   find-snake-entrypoints
   (distance-from-nearest-snakes-two-or-less current-position)))


(defn count-if-lucky-roll [state board]
  (let [current-position (:current-position state)
        miss-snake (miss-snake? board current-position)
        step-on-ladder (:step-on-ladder state)]
    (if (or step-on-ladder miss-snake)
      (update-in state [:lucky-rolls] (fn [lucky-rolls-counter] (inc lucky-rolls-counter)))
      state)))

(defn count-if-unlucky-roll [state]
  (if (:step-on-snake state)
    (update-in state [:unlucky-rolls] (fn [unlucky-rolls-counter] (inc unlucky-rolls-counter)))
    state))
(ns snake-and-ladders.move-stats
  (:require [snake-and-ladders.board :refer [find-snake-entrypoints]]))

(defn distance-from-nearest-snakes-two-or-less [square snake-entry-points]
  (if (not-empty snake-entry-points)
    (let [snake-after-selected-square (first (filter (fn [x] (> x square)) snake-entry-points))
          snake-before-selected-square  (first (filter (fn [x] (< x square)) snake-entry-points))]
      (or (and snake-after-selected-square (<= (- snake-after-selected-square square) 2))
          (and snake-before-selected-square (<= (- square snake-before-selected-square) 2))))
    false))

(defn miss-snake? [board current-position]
  (->>
   board
   find-snake-entrypoints
   (distance-from-nearest-snakes-two-or-less current-position)))

(defn update-if-next-roll-can-be-strike [state previous-position]
  (let [position (:current-position state)]
    (if (and (>= position 93) (< previous-position 93))
      (update-in state [:turns-in-strike-teritory] (fn [counter] (inc counter)))
      state)))

(defn lucky-strike? [state]
  (let [winning-spot? (= 99 (:current-position state))
        achieved-within-1-turn? (= (:turns-in-strike-teritory state) 1)]
    (and winning-spot? achieved-within-1-turn?)))

(defn count-if-lucky-roll [state board]
  (let [current-position (:current-position state)
        strike? (lucky-strike? state)
        miss-snake (miss-snake? board current-position)
        step-on-ladder (:step-on-ladder state)]
    (if (or step-on-ladder miss-snake strike?)
      (update-in state [:lucky-rolls] (fn [lucky-rolls-counter] (inc lucky-rolls-counter)))
      state)))

(defn count-if-unlucky-roll [state]
  (if (:step-on-snake state)
    (update-in state [:unlucky-rolls] (fn [unlucky-rolls-counter] (inc unlucky-rolls-counter)))
    state))
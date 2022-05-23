(ns snake-and-ladders.move
  (:require [snake-and-ladders.board :refer [find-snakes find-modifier-entrypoints]]))

(defn update-turns [previous-turns roll]
  (let [last-turn (last previous-turns)
        is-last-roll-six (= (last last-turn) 6)]
    (if is-last-roll-six
      (conj (drop-last previous-turns) (conj last-turn roll))
      (conj previous-turns [roll]))))

(defn distance-from-nearest-snakes-two-or-less [tile snake-entry-points]
  (if (not-empty snake-entry-points)
    (let [snake-entry-after-selected-tile (first (filter (fn [x] (> x tile)) snake-entry-points))
          snake-entry-before-selected-tile  (first (filter (fn [x] (< x tile)) snake-entry-points))]
      (or (and snake-entry-after-selected-tile (<= (- snake-entry-after-selected-tile tile) 2))
          (and snake-entry-before-selected-tile (<= (- tile snake-entry-before-selected-tile) 2))))
    false))

(defn single-attempt-at-last-roll
  [turns]
  (let [last-turn (last turns)
        second-last-turn (last (drop-last turns))]
    (> (reduce + (flatten [last-turn second-last-turn])) 6)))

(defn check-final-rolls [current-position turns]

  (if (and (= 99 current-position) (or (= (count turns) 1)
                                       (single-attempt-at-last-roll turns)))
    true
    false))

(defn count-if-lucky-roll [state board]
  (let [snakes (find-snakes board)
        snake-entries (find-modifier-entrypoints snakes)
        close-miss-snake (distance-from-nearest-snakes-two-or-less (:current-position state) snake-entries)
        one-attempt-at-final-roll (check-final-rolls (:current-position state) (:turns state))]
    (if (or close-miss-snake one-attempt-at-final-roll)
      (update-in state [:lucky-rolls] (fn [lucky-rolls-counter] (inc lucky-rolls-counter)))
      state)))

(defn apply-modifier [state {:keys [snake ladder]}]
  (cond
    snake (-> state
              (update-in [:current-position] (fn [_] (second snake)))
              (update-in [:unlucky-rolls] (fn [unlucky-rolls-counter] (inc unlucky-rolls-counter)))
              (update-in [:slides]  (fn [previous-slides] (conj previous-slides snake))))
    ladder (-> state
               (update-in [:current-position] (fn [_] (second ladder)))
               (update-in [:lucky-rolls] (fn [unlucky-rolls-counter] (inc unlucky-rolls-counter)))
               (update-in [:climbs] (fn [previous-climbs] (conj previous-climbs ladder))))))

(defn update-position [state board roll]
  (let [previous-tile (:current-position state)
        attempted-tile (+ previous-tile roll)
        tile (if (> attempted-tile 99)
               previous-tile
               attempted-tile)
        has-field-modifier (not-empty (nth board tile))]
    (if has-field-modifier
      (apply-modifier state (nth board tile))
      (update-in state [:current-position] (fn [_] tile)))))

(defn make-move [state board roll]
  (->
   (update-position state board roll)

   (update-in  [:turns] (fn [previous-turns] (update-turns previous-turns roll)))
   (count-if-lucky-roll board)))
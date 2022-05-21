(ns snake-and-ladders.move)

(defn update-turns [previous-turns roll]
  (let [last-turn (last previous-turns)
        is-last-roll-six (= (last last-turn) 6)]
    (if is-last-roll-six
      (conj (drop-last previous-turns) (conj last-turn roll))
      (conj previous-turns [roll]))))

(defn apply-modifier [state {:keys [snake ladder]}]
  (cond
    snake (-> state
              (update-in [:current-position] (fn [_] (second snake)))
              (update-in [:unlucky-rolls] (fn[_] 1))
              (update-in [:slides]  (fn [previous-slides] (conj previous-slides snake))))
    ladder (-> state
               (update-in [:current-position] (fn [_] (second ladder)))
               (update-in [:climbs] (fn [previous-climbs] (conj previous-climbs ladder))))))

(defn update-position [state board roll]
  (let [previous-tile (:current-position state)
        attempted-tile (+ previous-tile roll)
        tile (if (> attempted-tile 100)
               previous-tile
               attempted-tile)
        has-field-modifier (not-empty (nth board tile))]
    (if has-field-modifier
      (apply-modifier state (nth board tile))
      (update-in state [:current-position] (fn [_] tile)))))

(defn make-move [state board roll]
  (->
   (update-position state board roll)
   (update-in  [:turns] (fn [previous-turns] (update-turns previous-turns roll)))))
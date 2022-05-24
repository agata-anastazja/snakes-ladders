(ns snake-and-ladders.move)

(defn update-turns [previous-turns roll]
  (let [last-turn (last previous-turns)
        is-last-roll-six (= (last last-turn) 6)]
    (if is-last-roll-six
      (conj (pop previous-turns) (conj last-turn roll))
      (conj previous-turns [roll]))))

(defn apply-modifier [state {:keys [snake ladder]}]
  (cond
    snake (-> state
              (update-in [:current-position] (fn [_] (second snake)))
              (update-in [:step-on-snake]  (fn [_] true))
              (update-in [:slides]  (fn [previous-slides] (conj previous-slides snake))))
    ladder (-> state
               (update-in [:current-position] (fn [_] (second ladder)))
               (update-in [:step-on-ladder] (fn [_] true))
               (update-in [:climbs] (fn [previous-climbs] (conj previous-climbs ladder))))))

(defn update-position [state board roll]
  (let [previous-square (:current-position state)
        attempted-square (+ previous-square roll)
        square (if (> attempted-square 99)
                 previous-square
                 attempted-square)
        has-square-modifier (not-empty (nth board square))]
    (if has-square-modifier
      (apply-modifier state (nth board square))
      (update-in state [:current-position] (fn [_] square)))))

(defn make-move [state board roll]
  (->
   state
   (update-position board roll)
   (update-in  [:turns] (fn [previous-turns] (update-turns previous-turns roll)))))
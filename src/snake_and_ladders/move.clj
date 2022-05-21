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
             (update-in [:slides]  (fn [previous-slides] (conj previous-slides snake))))
    ladder (-> state
              (update-in [:current-position] (fn [_] (second ladder))))))

(defn update-position [state board roll]
  (let [tile (+ (:current-position state) roll)
        field-modifier (nth board tile)]
    (if (empty? field-modifier)
      (update-in state [:current-position] (fn[_] tile))
      (apply-modifier state field-modifier))))

(defn make-move [state board roll]
  (->
   (update-position state board roll)
   (update-in  [:turns] (fn [previous-turns] (update-turns previous-turns roll)))))
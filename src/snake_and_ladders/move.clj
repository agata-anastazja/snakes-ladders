(ns snake-and-ladders.move)

(defn update-turns [previous-turns roll]
  (let [last-turn (last previous-turns)
        is-last-roll-six (= (last last-turn) 6)]
    (if is-last-roll-six
      (conj (drop-last previous-turns) (conj last-turn roll))
      (conj previous-turns [roll]))))

(defn apply-modifier [field-modifier]
  (second (:snake field-modifier)))

(defn update-position [previous-position board roll]
  (let [tile (+ previous-position roll)
        field-modifier (nth board tile)]
    (if (empty? field-modifier)
      tile
      (apply-modifier field-modifier))))

(defn make-move [state board roll]
  (->
   (update-in state [:current-position] (fn [previous-position] (update-position previous-position board roll)))
   (update-in  [:turns] (fn [previous-turns] (update-turns previous-turns roll)))))
(ns snake-and-ladders.move)

(defn update-turns [previous-turns roll]
  (let [last-turn (last previous-turns)
        is-last-roll-six (= (last last-turn) 6)]
    (if is-last-roll-six
      (conj (drop-last previous-turns) (conj last-turn roll))
      (conj previous-turns [roll]))))

(defn make-move [state board roll]
  (->
   (update-in state [:current-position] (fn [previous-position] (+ previous-position roll)))
   (update-in  [:turns] (fn [previous-turns] (update-turns previous-turns roll)))))
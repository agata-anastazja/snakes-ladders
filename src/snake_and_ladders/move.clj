(ns snake-and-ladders.move)

(defn make-move [board state roll]
  (->
   (update-in state [:current-position] (fn [previous-position] (+ previous-position roll)))
   (update-in  [:turns] (fn [previous-turns] (conj previous-turns [roll])))))
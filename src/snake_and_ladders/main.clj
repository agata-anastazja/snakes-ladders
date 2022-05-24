(ns snake-and-ladders.main
  (:require [snake-and-ladders.core :as core]
            [snake-and-ladders.game-stats :as game-stats]))

(defn main [board rolls]
  (let [games (map #(core/play board %) rolls)
        each-game-stats (map #(game-stats/produce-game-stats %) games)]
    (game-stats/simulation-stats each-game-stats)))
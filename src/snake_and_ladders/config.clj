(ns snake-and-ladders.config
  (:require [environ.core :refer [env]]))

(def board-size
  (env :board-size 101))
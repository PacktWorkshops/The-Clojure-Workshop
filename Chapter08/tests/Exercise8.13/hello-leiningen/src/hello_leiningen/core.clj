(ns hello-leiningen.core
  (:require [java-time :as time])
  (:gen-class))

(defn -main
  "Display current local time"
  [& args]
  (println (time/local-time)))

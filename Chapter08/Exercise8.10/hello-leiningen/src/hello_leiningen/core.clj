(ns hello-leiningen.core
  (:require [clojure.string :as str]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
    (-> (str/join " " args)
      (str/replace "melon" "banana")
      (str/replace "apple" "orange")
      println))
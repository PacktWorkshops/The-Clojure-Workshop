(ns json-parser.core
    (:require [cheshire.core :as json])
    (:gen-class))

(defn generate-json-from-hash [hash]
      (json/generate-string hash))

(generate-json-from-hash {:name "John" :occupation "programmer"})

(defn generate-hash-from-json [json]
      (json/parse-string json))

(generate-hash-from-json "{\"name\":\"Mike\",\"occupation\":\"carpenter\"}")

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

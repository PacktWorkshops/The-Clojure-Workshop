(ns packt-clj.chapter-5-tests.exercise-5-06
  (:require 
   [clojure.java.io :as io]
   [clojure.data.csv :as csv]
   [semantic-csv.core :as sc]))


(defn win-loss-by-player [csv]
  (with-open [r (io/reader csv)]
    (->> (csv/read-csv r)
         sc/mappify
         (reduce (fn [acc {:keys [winner_slug loser_slug]}]
                   (-> acc
                       (update-in [winner_slug :wins]
                                  (fn [wins] (inc (or wins 0))))
                       (update-in [loser_slug :losses]
                                  (fn [losses] (inc (or losses 0))))))
                 {}))))


(ns packt-clj.exercise-4-11-test
  (:require  [clojure.test :as t :refer [deftest is testing]]
             [clojure.java.io :as io]
             [clojure.data.csv :as csv]))

;;; For convenience, we reference the csv file as a resource.
(def data (io/file (io/resource "match_scores_1991-2016_unindexed_csv.csv")))

(deftest reading-a-csv-file
  (let [results (with-open [r (io/reader data)]
                  (->> (csv/read-csv r)
                       (map #(nth % 7))
                       (take 6)
                       doall))]
    (is (= 6 (count results)))))

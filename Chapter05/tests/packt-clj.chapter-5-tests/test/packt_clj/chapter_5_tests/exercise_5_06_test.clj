(ns packt-clj.chapter-5-tests.exercise-5-06-test
  (:require
   [clojure.test :as t :refer [deftest is testing]]
   [clojure.java.io :as io]
   [packt-clj.chapter-5-tests.exercise-5-06 :as exo]))

;;; For convenience, we reference the csv file as a resource.
(def data (io/file (io/resource "match_scores_1991-2016_unindexed_csv.csv")))

(deftest win-loss
  (let [win-loss (exo/win-loss-by-player data)]
    (is (map? win-loss))
    (is (every? (comp map? second) win-loss))
    ;; We use `or` here becomes some players might only have losses or only have wins
    (is (every? (fn [[_ {:keys [wins losses]}]] (or (integer? wins) (integer? losses)))
                win-loss))))



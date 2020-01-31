(ns packt-clj.chapter-5-tests.exercise-5-05-test
  (:require
   [clojure.test :as t :refer [deftest is testing]]
   [clojure.java.io :as io]
   [packt-clj.chapter-5-tests.exercise-5-05 :as exo]))


;;; For convenience, we reference the csv file as a resource.
(def data (io/file (io/resource "match_scores_1991-2016_unindexed_csv.csv")))


(deftest tournament-groups
  (let [groups (exo/tennis-csv->tournament-groups data)]
    (is (map? groups) "A big map is returned")
    (is (every? sequential? (vals groups)) "All the values are lists or vectors")
    (is (contains? groups "adelaide") "The adelaide group is present")))


(deftest tournament-match-counts
  (let [group-counts (exo/tennis-csv->tournament-match-counts data)]
    (is (map? group-counts))
    (is (every? integer? (vals group-counts)))
    (is (= 566 (get group-counts "adelaide")))))


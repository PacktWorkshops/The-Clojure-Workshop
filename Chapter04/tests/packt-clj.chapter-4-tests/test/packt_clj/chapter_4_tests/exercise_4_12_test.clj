(ns packt-clj.exercise-4-12-test
  (:require
   [clojure.test :as t :refer [deftest is testing]]
   [clojure.data.csv :as csv]
   [clojure.java.io :as io]
   [semantic-csv.core :as sc]
   [packt-clj.chapter-4-tests.exercise-4-12 :as exo]))

;;; For convenience, we reference the csv file as a resource.
(def data (io/file (io/resource "match_scores_1991-2016_unindexed_csv.csv")))

(deftest read-a-single-line
  (is (map? (exo/first-match data))))

(deftest five-lines
  (let [simple-five (exo/five-matches data)]
    (is (= (count simple-five) 5))
    (is (string? (:winner_sets_won (first simple-five))))
    (is (string? (:loser_name (first simple-five))))))


(deftest casting-to-integers
  (is (integer? (:winner_sets_won (first (exo/five-matches-int-sets data))))))

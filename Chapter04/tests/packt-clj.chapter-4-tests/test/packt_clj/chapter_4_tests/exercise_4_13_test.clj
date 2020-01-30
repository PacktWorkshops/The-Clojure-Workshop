(ns packt-clj.exercise-4-13-test
  (:require
   [clojure.test :as t :refer [deftest is testing]]
   [clojure.data.csv :as csv]
   [clojure.java.io :as io]
   [semantic-csv.core :as sc]
   [packt-clj.chapter-4-tests.exercise-4-13 :as ex]))


;;; For convenience, we reference the csv file as a resource.
(def data (io/file (io/resource "match_scores_1991-2016_unindexed_csv.csv")))

(deftest federer
  (is (= "Roger Federer"
         (:winner_name (first (ex/federer-wins data))))))

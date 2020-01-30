(ns packt-clj.chapter-4-tests.exercise-4-15-test
  (:require
   [clojure.test :as t :refer [deftest is testing]]
   [clojure.data.csv :as csv]
   [clojure.java.io :as io]
   [semantic-csv.core :as sc]
   [packt-clj.chapter-4-tests.exercise-4-15 :as exo]))


;;; For convenience, we reference the csv file as a resource.
(def data (io/file (io/resource "match_scores_1991-2016_unindexed_csv.csv")))


(deftest match-query
  (is (every?
        (fn [{:keys [winner_name loser_name]}] (or (= winner_name "Roger Federer")
                                         (= winner_name "Rafael Nadal")))
        (exo/match-query
          data
          #(and (= (hash-set (:winner_name %) (:loser_name %)) 
                   #{"Roger Federer" "Rafael Nadal"})
                (= 1 (- (:winner_sets_won %) (:loser_sets_won %))))))))

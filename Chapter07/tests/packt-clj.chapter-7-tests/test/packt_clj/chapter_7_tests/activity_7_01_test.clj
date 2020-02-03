(ns packt-clj.chapter-7-tests.activity-7-01-test
  (:require [packt-clj.chapter-7-tests.activity-7-01 :as act]
            [clojure.test :as t :refer [deftest is testing]]
            [clojure.java.io :as io]
            [packt-clj.chapter-7-tests.exercise-7-05 :as exo]
            [packt-clj.chapter-7-tests.exercise-7-03 :as exo-three]))

(def matches (exo-three/elo-db (io/file (io/resource "match_scores_1991-2016_unindexed_csv.csv"))  35))
(def federer (exo-three/match-tree-by-player matches "roger-federer"))


(deftest focus-history
  (let [history (act/focus-history federer
                                   "roger-federer"
                                   4
                                   2
                                   #(select-keys % [:winner_name :loser_name :winner_rating :loser_rating]))]
    (is (= "Roger Federer" (:winner_name (first history))))
    (is (= "Guido Pella" (:loser_name (first history))))
    (is (= "Marcus Willis" (:loser_name (ffirst (second history)))))
    (is (= "Daniel Evans" (:loser_name (ffirst (second (first (second history)))))))))



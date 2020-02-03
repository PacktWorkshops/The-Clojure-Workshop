(ns packt-clj.chapter-7-tests.exercise-7-04-test
  (:require [packt-clj.chapter-7-tests.exercise-7-04 :as exo]
            [clojure.test :as t :refer [deftest is testing]]
            [clojure.java.io :as io]
            [packt-clj.chapter-7-tests.exercise-7-03 :as exo-three]))


(def matches (exo-three/elo-db (io/file (io/resource "match_scores_1991-2016_unindexed_csv.csv"))  35))
(def federer (exo-three/match-tree-by-player matches "roger-federer"))


(deftest take-matches
  (testing "basic structure"
    (let [fed-matches (take 3 federer)]
      (is (= 2 (count federer)))
      (is (map? (first fed-matches)))
      (is (vector? (second fed-matches)))
      (is (and (seq? (first (second fed-matches)))
               (not (vector? (first (second fed-matches)))))))))



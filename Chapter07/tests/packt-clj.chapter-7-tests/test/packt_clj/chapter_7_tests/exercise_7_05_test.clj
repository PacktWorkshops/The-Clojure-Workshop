(ns packt-clj.chapter-7-tests.exercise-7-05-test
  (:require
   [clojure.test :as t :refer [deftest is testing]]
   [clojure.java.io :as io]
   [packt-clj.chapter-7-tests.exercise-7-05 :as exo]
   [packt-clj.chapter-7-tests.exercise-7-03 :as exo-three]))


(def matches (exo-three/elo-db (io/file (io/resource "match_scores_1991-2016_unindexed_csv.csv"))  35))
(def federer (exo-three/match-tree-by-player matches "roger-federer"))


(deftest new-version-of-take-matches
  (is (= [{:winner_slug "roger-federer" :loser_slug "guido-pella"}
          ['({:winner_slug "roger-federer" :loser_slug "marcus-willis"}
             [{:winner_slug "roger-federer" :loser_slug "daniel-evans"}
              {:winner_slug "pierre-hugues-herbert" :loser_slug "marcus-willis"}])
           '({:winner_slug "benjamin-becker" :loser_slug "guido-pella"}
             [{:winner_slug "dudi-sela" :loser_slug "benjamin-becker"}
              {:winner_slug "guido-pella" :loser_slug "diego-schwartzman"}])]]
         (exo/take-matches 3 federer #(select-keys % [:winner_slug :loser_slug])))))


(deftest matches-with-ratings
  (let [ms-with (exo/matches-with-ratings 3 federer)]
    (is (= "Roger Federer" (:winner_name (first ms-with))))
    (is (= "Guido Pella" (:loser_name (first ms-with))))
    (is (= "Marcus Willis" (:loser_name (ffirst (second ms-with)))))
    (is (= "Benjamin Becker" (:winner_name (first (second (second ms-with))))))
    (is (= "Guido Pella" (:loser_name (first (second (second ms-with))))))))




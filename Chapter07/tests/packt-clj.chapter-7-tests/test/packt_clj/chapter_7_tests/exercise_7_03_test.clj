(ns packt-clj.chapter-7-tests.exercise-7-03-test
  (:require [packt-clj.chapter-7-tests.exercise-7-03 :as exo]
            [clojure.test :as t :refer [deftest is testing]]
            [clojure.java.io :as io]))

;;; For convenience, we reference the csv file as a resource.
(def data (io/file (io/resource "match_scores_1991-2016_unindexed_csv.csv")))


(def matches (exo/elo-db data 35))

(deftest verify-matches                 
  (is (= 985 (int (:winner_rating (first matches))))))


(deftest player-in-match
  (is (exo/player-in-match? (first matches) "gael-monfils"))
  (is (not (exo/player-in-match? (first matches) "not-a-real-player"))))

(deftest match-tree-by-player
  (is (empty? (exo/match-tree-by-player matches "not-a-real-player")))
  (let [federer (exo/match-tree-by-player matches "roger-federer")]
    (is (seq? federer))
    (is (= 1129 (int (:winner_rating (first federer)))))))



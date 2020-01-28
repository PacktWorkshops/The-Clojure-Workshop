(ns packt-clj.tennis-test
  (:require
   [clojure.test :as t :refer [deftest is testing]]
   [packt-clj.tennis :as tennis]))

(def tennis-data-csv "../tennis-data/match_scores_1991-2016_unindexed_csv.csv")


(deftest verify-first-match
  (is (map? (tennis/first-match tennis-data-csv)))
  (is (= "Nicklas Kulti" (:winner_name (tennis/first-match tennis-data-csv)))))

(deftest verify-five-matches
  (is (= 5 (count (tennis/five-matches tennis-data-csv)))))

(deftest verify-five-matches-int-sets
  (is (= 5 (count (tennis/five-matches-int-sets tennis-data-csv))))
  (is (integer? (:winner_sets_won (first (tennis/five-matches-int-sets tennis-data-csv))))))


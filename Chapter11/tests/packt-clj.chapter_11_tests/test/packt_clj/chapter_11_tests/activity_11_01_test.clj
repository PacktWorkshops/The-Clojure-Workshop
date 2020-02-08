(ns packt-clj.chapter-11-tests.activity-11-01-test
  (:require
   [clojure.test :as t :refer [deftest is testing]]
   [packt-clj.chapter-11-tests.activity-11-01 :as act]
   [clojure.java.io :as io]
   [clojure.data.csv :as csv]
   [semantic-csv.core :as sc]))


;;; For convenience, we reference the csv file as a resource.
(def data (io/file (io/resource "match_scores_1991-2016_unindexed_csv.csv")))

(deftest maybe-select-keys
  (is (= {:a 1} (act/maybe-select-keys {:a 1} [])))
  (is (= {:a 1 :b 2} (act/maybe-select-keys {:a 1 :b 2 :c 3} [:a :b]))))

(deftest blowouts-with-tennis-csv-macro
  (is (= 11
         (count
           (act/with-tennis-csv data
             {:winner_games_won sc/->int :loser_games_won sc/->int}
             [:winner_name :loser_name :games_diff]
             (map #(assoc % :games_diff (- (:winner_games_won %) (:loser_games_won %))))
             (filter #(> (:games_diff %) 16)))))))


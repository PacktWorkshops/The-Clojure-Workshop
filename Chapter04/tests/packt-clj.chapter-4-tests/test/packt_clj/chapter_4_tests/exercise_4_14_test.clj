(ns packt-clj.chapter-4-tests.exercise-4-14-test
  (:require
   [clojure.test :as t :refer [deftest is testing]]
   [clojure.data.csv :as csv]
   [clojure.java.io :as io]
   [semantic-csv.core :as sc]
   [packt-clj.chapter-4-tests.exercise-4-14 :as exo]))

;;; For convenience, we reference the csv file as a resource.
(def data (io/file (io/resource "match_scores_1991-2016_unindexed_csv.csv")))

(deftest match-query
  (is (= {:winner_name "Lucas Arnold Ker",          
          :loser_name "Roger Federer",
          :winner_sets_won "2",
          :loser_sets_won "0",
          :winner_games_won "12",
          :loser_games_won "8",
          :tourney_year_id "1998-314",
          :tourney_slug "gstaad"}
         (first (exo/match-query
                  data
                 #(or (= "Roger Federer" (:winner_name %))
                      (= "Roger Federer" (:loser_name %))))))))


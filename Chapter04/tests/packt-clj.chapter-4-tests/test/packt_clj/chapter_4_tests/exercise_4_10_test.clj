(ns packt-clj.exercise-4-10-test
  (:require  [clojure.test :as t :refer [deftest is testing]]
             [clojure.java.io :as io]
             [clojure.data.csv :as csv]))


(deftest reading-from-csv
  (is (= ["tourney_year_id"          
           "tourney_order"
           "tourney_slug"
           "tourney_url_suffix"
           "tourney_round_name"
           "round_order"
           "match_order"
           "winner_name"
           "winner_player_id"
           "winner_slug"
           "loser_name"
           "loser_player_id"
           "loser_slug"
           "winner_seed"
           "loser_seed"
           "match_score_tiebreaks"
           "winner_sets_won"
           "loser_sets_won"
           "winner_games_won"
           "loser_games_won"
           "winner_tiebreaks_won"
           "loser_tiebreaks_won"
           "match_id"
           "match_stats_url_suffix"]
         (with-open [r (io/reader (io/file (io/resource "match_scores_1991-2016_unindexed_csv.csv")))]
           (first (csv/read-csv r)))))

  (is (= 95360
         (with-open [r (io/reader (io/file (io/resource "match_scores_1991-2016_unindexed_csv.csv")))]
           (count (csv/read-csv r))))))

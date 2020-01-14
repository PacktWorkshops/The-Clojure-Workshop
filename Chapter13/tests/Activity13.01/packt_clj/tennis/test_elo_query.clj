(ns packt-clj.tennis.test-elo-query
  (:require
    [clojure.java.io :as io]
    [clojure.test :refer :all]
    [packt-clj.tennis.database :as database]
    [packt-clj.tennis.elo :as elo]
    [packt-clj.tennis.fixtures :as fixtures]
    [packt-clj.tennis.ingest :as ingest]
    [packt-clj.tennis.query :as query]))

(use-fixtures :each fixtures/db-fixture)

(deftest test-max-elo
  (ingest/historic database/db (io/resource "match_scores_1991-2016_unindexed_csv.csv"))
  (elo/persist-all database/db)
  (is (= {:max-rating  2974.61M
          :player-name "Novak Djokovic"}
         (query/select-max-elo database/db))))

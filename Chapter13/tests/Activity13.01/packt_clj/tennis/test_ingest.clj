(ns packt-clj.tennis.test-ingest
  (:require
    [clojure.java.io :as io]
    [clojure.java.jdbc :as jdbc]
    [clojure.test :refer :all]
    [packt-clj.tennis.database :as database]
    [packt-clj.tennis.fixtures :as fixtures]
    [packt-clj.tennis.ingest :as ingest]))

(use-fixtures :each fixtures/db-fixture)

(deftest test-historic-load

  (ingest/historic database/db (io/resource "match_scores_1991-2016_unindexed_csv.csv"))

  (is (= [{:1 3483}] (jdbc/query database/db ["select count(*) from player"])))
  (is (= [{:1 95359}] (jdbc/query database/db ["select count(*) from tennis_match"])))
  (is (= [{:1 0}] (jdbc/query database/db ["select count(*) from elo"]))))

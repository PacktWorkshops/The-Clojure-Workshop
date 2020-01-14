(ns packt-clj.tennis.test-database
  (:require
    [clojure.java.jdbc :as jdbc]
    [clojure.test :refer :all]
    [packt-clj.tennis.database :as database]
    [packt-clj.tennis.fixtures :as fixtures]))

(use-fixtures :each fixtures/db-fixture)

(deftest test-database-schema-load
  (is (= [] (jdbc/query database/db ["select * from player"])))
  (is (= [] (jdbc/query database/db ["select * from tennis_match"])))
  (is (= [] (jdbc/query database/db ["select * from elo"]))))

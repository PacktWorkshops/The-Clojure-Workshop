(ns packt-clj.tennis.fixtures
  (:require
    [clojure.java.jdbc :as jdbc]
    [clojure.test :refer :all]
    [packt-clj.tennis.database :as database]))

(defn db-fixture
  [f]
  (database/load)
  (f)
  (jdbc/execute! database/db ["drop table elo"  ])
  (jdbc/execute! database/db ["drop table tennis_match"])
  (jdbc/execute! database/db ["drop table player"]))

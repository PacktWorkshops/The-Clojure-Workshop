(ns packt-clj.tennis.ingest
  (:require
    [clojure.java.jdbc :as jdbc]
    [packt-clj.tennis.parse :as parse]))

(defn historic
  [db file-path]
  (let [{:keys [players matches]} (parse/historic file-path)]
    (jdbc/insert-multi! db :player players)
    (jdbc/insert-multi! db :tennis_match matches)))
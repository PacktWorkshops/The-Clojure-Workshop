(ns packt-clj.fitness.ingest
  (:require
    [clojure.java.jdbc :as jdbc]))

(defn user
  [db app_user]
  (first (jdbc/insert! db :app_user app_user)))

(defn activity
  [db activity]
  (first (jdbc/insert! db :activity activity)))

(defn tennis-match
  [db tennis-match]
  (first (jdbc/insert! db :tennis_match tennis-match)))

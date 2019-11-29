(ns packt-clj.exercises.queries
  (:require
    [clojure.java.jdbc :as jdbc]
    [clojure.string :as str]))

(defn all-users
  [db]
  (jdbc/query db ["select * from app_user"]))

(defn all-activities
  [db]
  (jdbc/query db ["select * from activity"]))

(defn all-users-upper-case
  [db]
  (jdbc/query db ["select * from app_user"] {:keywordize? false :identifiers str/upper-case}))

(defn all-users-upper-case-qualified
  [db]
  (jdbc/query db ["select * from app_user"] {:identifiers str/upper-case :qualifier "app_user"}))

(defn all-users-upper-case-qualified-keys-only
  [db]
  (-> (jdbc/query db ["select * from app_user"] {:identifiers str/upper-case :qualifier "app_user"})
      first
      keys))

(defn all-activities-as-arrays
  [db]
  (jdbc/query db ["select * from activity"] {:as-arrays? true}))


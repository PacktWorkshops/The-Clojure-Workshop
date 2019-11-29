(ns packt-clj.exercises.update-delete-data
  (:require
    [clojure.java.jdbc :as jdbc]))

(defn update-agassi
  [db]
  (jdbc/update! db :app_user {:weight 78} ["first_name = 'Andre' and surname = 'Agassi'"]))

(defn delete-agassi
  [db]
  (jdbc/delete! db :app_user ["first_name = 'Andre' and surname = 'Agassi'"]))

(defn all-users
  [db]
  (jdbc/query db ["select * from app_user"]))

(defn all-activities
  [db]
  (jdbc/query db ["select * from activity"]))
(ns packt-clj.exercises.data-insertion
  (:require
    [clojure.java.jdbc :as jdbc]))

; option 1
(defn insert-user
  [db]
  (jdbc/insert!
    db
    :app_user
    {:first_name "Andre"
     :surname    "Agassi"
     :height     180
     :weight     80}))

; option 2
(defn insert-user
  [db]
  (jdbc/insert!
    db
    :app_user
    [:first_name :surname :height :weight]
    ["Andre" "Agassi" 180 80]))

(defn insert-activities
  [db]
  (jdbc/insert-multi!
    db
    :activity
    [{:activity_type "run" :distance 8.67 :duration 2520 :user_id 1}
     {:activity_type "cycle" :distance 17.68 :duration 2703 :user_id 1}]))


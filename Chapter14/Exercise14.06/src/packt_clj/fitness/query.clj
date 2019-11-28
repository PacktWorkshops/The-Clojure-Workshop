(ns packt-clj.fitness.query
  (:require
    [clojure.java.jdbc :as jdbc]
    [java-time :as t]
    [medley.core :as medley]))


(defn all-users
  [db]
  (jdbc/query db ["select * from app_user"]))

(defn user
  [db user-id]
  (jdbc/query db [(str "select * from app_user where id = " user-id)]))

(defn all-activities
  [db]
  (jdbc/query db ["select * from activity"]))

(defn activity
  [db activity-id]
  (jdbc/query db [(str "select * from activity where id = " activity-id)]))

(defn activities-by-user
  [db user-id]
  (jdbc/query db [(str "select * from activity where user_id = " user-id)]))

(defn most-active-user
  [db]
  (jdbc/query
    db
    ["select au.first_name, au.surname, a.duration from app_user au, activity a where au.id = a.user_id "]
    {:row-fn        (fn [{:keys [first_name surname duration]}] {:name     (str first_name " " surname)
                                                                 :duration duration})
     :result-set-fn (fn [rs]
                      (->> rs
                           (group-by :name)
                           (medley/map-vals #(apply + (map :duration %)))
                           (sort-by val)
                           last))}))

(defn monthly-activity-by-user
  [db user-id]
  (jdbc/query
    db
    [(str "select au.first_name, au.surname, a.duration, a.activity_type, a.distance, a.activity_date from app_user au, activity a where au.id = a.user_id and a.user_id = " user-id)]
    {:row-fn        (fn [row] (update row :activity_date t/local-date))
     :result-set-fn (fn [rs]
                      (reduce
                        (fn [acc {:keys [activity_date activity_type distance duration first_name surname] :as row}]
                          (let [month-year (t/as activity_date :month-of-year :year)]
                            (update-in acc [month-year activity_type] #(merge-with + % (select-keys row [:distance :duration])))))
                        (sorted-map)
                        rs))}))

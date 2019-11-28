(ns packt-clj.exercises.application-layer-integration
  (:require
    [clj-http.client :as http]
    [packt-clj.fitness.api :as api]))

; commented out to avoid spinning up a jetty server on compilation
#_(def app (api/run))

(defn add-new-user
  []
  (-> (http/post "http://localhost:8080/users"
                 {:body (pr-str {:first_name "Boris"
                                 :surname    "Becker"
                                 :height     191
                                 :weight     85})})
      :headers
      (get "Link")))

(defn add-new-activity
  []
  (-> (http/post "http://localhost:8080/activities"
                 {:body (pr-str {:user_id       4
                                 :activity_type "run"
                                 :activity_date "2019-03-25"
                                 :distance      4970
                                 :duration      1200})})
      :headers
      (get "Link")))


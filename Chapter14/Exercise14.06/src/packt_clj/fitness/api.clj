(ns packt-clj.fitness.api
  (:require
    [clojure.edn :as edn]
    [compojure.core :refer [context defroutes DELETE GET PUT POST]]
    [compojure.route :as route]
    [muuntaja.middleware :as middleware]
    [packt-clj.fitness.ingest :as ingest]
    [packt-clj.fitness.query :as query]
    [packt-clj.fitness.schema :as schema]
    [ring.adapter.jetty :refer [run-jetty]]
    [ring.middleware.params :as params]))

(defroutes routes
           (context "/users" []
             (GET "/" []
               {:body (query/all-users schema/db)})
             (POST "/" req
               (let [ingest-result (ingest/user schema/db (edn/read-string (slurp (:body req))))]
                 {:status  201
                  :headers {"Link" (str "/users/" (:1 ingest-result))}}))
             (GET "/:id" [id]
               (when-first [user (query/user schema/db id)]
                 {:body user}))
             (GET "/:id/activities" [id]
               {:body (query/activities-by-user schema/db id)}))
           (context "/activities" []
             (GET "/" []
               {:body (query/all-activities schema/db)})
             (POST "/" req
               (let [ingest-result (ingest/activity schema/db (edn/read-string (slurp (:body req))))]
                 {:status  201
                  :headers {"Link" (str "/activities/" (:1 ingest-result))}}))
             (GET "/:id" [id]
               (when-first [activity (query/activity schema/db id)]
                 {:body activity})))
           (context "/reports" [report-type id]
             (GET "/" []
               {:body (case report-type
                        "most-active-user" (query/most-active-user schema/db)
                        "monthly-activity-by-user" (query/monthly-activity-by-user schema/db id)
                        nil)}))
           (route/not-found "Not found"))

(defn run
  []
  (run-jetty
    (-> routes
        middleware/wrap-format
        params/wrap-params)
    {:port  8080
     :join? false}))

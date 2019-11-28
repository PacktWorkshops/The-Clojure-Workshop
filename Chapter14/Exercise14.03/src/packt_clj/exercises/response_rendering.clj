(ns packt-clj.exercises.response-rendering
  (:require
    [compojure.core :refer [defroutes GET]]
    [compojure.route :as route]
    [muuntaja.middleware :as middleware]
    [ring.adapter.jetty :refer [run-jetty]]))

(defroutes routes
           (GET "/string" request "a simple string response")
           (GET "/data-structure" request
             {:body {:a 1
                     :b #{2 3 4}
                     :c "nested data structure"}})
           (route/not-found "Not found"))

(defn run
  []
  (run-jetty
    (middleware/wrap-format routes)
    {:port  8080
     :join? false}))

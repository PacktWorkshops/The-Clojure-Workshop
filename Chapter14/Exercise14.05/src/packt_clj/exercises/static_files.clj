(ns packt-clj.exercises.static-files
  (:require
    [compojure.core :refer [defroutes]]
    [compojure.route :as route]
    [ring.adapter.jetty :refer [run-jetty]]))

(defroutes routes
           (route/files "/files/" {:root "./resources/"})
           (route/not-found "Not found"))

(defn run
  []
  (run-jetty
    routes
    {:port  8080
     :join? false}))


(ns packt-clj.exercises.request-routing
  (:require
    [compojure.core :refer [defroutes GET]]
    [ring.adapter.jetty :refer [run-jetty]]
    [compojure.route :as route]))

(def route
  (GET "/" request "Hello World"))

(defn run
  []
  (run-jetty route
             {:port  8080
              :join? false}))

(defroutes routes
           (GET "/route-1" request "Hello from route-1")
           (GET "/route-2" request "Hello from route-2")
           (route/not-found "Not the route you are looking for"))

(defn run-multi-routes
  []
  (run-jetty routes {:port  8080
                     :join? false}))

(defroutes routes-bad-order
           (route/not-found "Not the route you are looking for")
           (GET "/route-1" request "Hello from route-1")
           (GET "/route-2" request "Hello from route-2"))

(defn run-bad-order
  []
  (run-jetty routes-bad-order {:port  8080
                               :join? false}))


(defn stop [app] (.stop app))

; commented out to avoid spinning up a jetty server on compilation
#_(def app (run))
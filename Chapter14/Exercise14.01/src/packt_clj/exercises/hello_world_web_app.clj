(ns packt-clj.exercises.hello-world-web-app
  (:require
    [ring.adapter.jetty :refer [run-jetty]]))

(defn handler [request]
  {:status 200
   :body "Hello World"})

(def app (run-jetty handler {:port 8080
                             :join? false}))

(defn stop [app] (.stop app))

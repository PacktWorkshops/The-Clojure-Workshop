(ns packt-clj.exercises.request-body
  (:require
    [clj-http.client :as http]
    [clojure.data.json :as json]
    [clojure.edn :as edn]
    [compojure.core :refer [defroutes DELETE GET PUT]]
    [compojure.route :as route]
    [muuntaja.middleware :as middleware]
    [ring.adapter.jetty :refer [run-jetty]]))

(def db (atom {}))
(defroutes routes
           (GET "/data-structure" request
             (when-let [data-structure (@db :data)]
               {:body data-structure}))
           (PUT "/data-structure" request
             (swap! db assoc :data (:body-params request))
             {:status 201})
           (DELETE "/data-structure" request
             (swap! db dissoc :data))
           (route/not-found "Not found"))

(defn run
  []
  (run-jetty
    (middleware/wrap-format routes)
    {:port  8080
     :join? false}))

(defn persist-data-structure-json
  []
  (-> (http/put "http://localhost:8080/data-structure"
                {:content-type :application/json
                 :body         (json/write-str {:a 1
                                                :b #{2 3 4}})})
      :status))

(defn retrieve-data-structure-edn
  []
  (-> (http/get "http://localhost:8080/data-structure"
                {:accept :application/edn})
      :body
      edn/read-string))

(defn persist-data-structure-edn
  []
  (-> (http/put "http://localhost:8080/data-structure"
                {:content-type :application/edn
                 :body         (pr-str {:a 1
                                        :b #{2 3 4}})})
      :status))

(defn delete-data-structure
  []
  (-> (http/delete "http://localhost:8080/data-structure")
      :status))
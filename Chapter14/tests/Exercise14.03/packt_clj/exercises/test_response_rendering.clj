(ns packt-clj.exercises.test-response-rendering
  (:require
    [clj-http.client :as client]
    [clojure.edn :as edn]
    [clojure.test :refer :all]
    [packt-clj.exercises.response-rendering :as response-rendering]))

(deftest test-response-rendering

  (let [app (response-rendering/run)]
    (is (= {:status 200
            :body "a simple string response"}
           (select-keys (client/get "http://localhost:8080/string") [:body :status])))
    (is (= {:status 200
            :body {:a 1
                   :b #{2 3 4}
                   :c "nested data structure"}}
           (-> (client/get "http://localhost:8080/data-structure" {:accept :application/edn})
               (select-keys [:body :status])
               (update :body edn/read-string)
               )))
    (.stop app)))

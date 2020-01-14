(ns packt-clj.exercises.test-request-routing
  (:require
    [clj-http.client :as client]
    [clojure.test :refer :all]
    [packt-clj.exercises.request-routing :as request-routing]))

(deftest test-hello-world

  (let [app (request-routing/run)]
    (is (= {:status 200
            :body   "Hello World"}
           (select-keys (client/get "http://localhost:8080") [:body :status])))
    (request-routing/stop app)))

(deftest test-multi

  (let [app (request-routing/run-multi-routes)]
    (is (= {:status 200
            :body   "Hello from route-1"}
           (select-keys (client/get "http://localhost:8080/route-1") [:body :status])))
    (is (= {:status 200
            :body   "Hello from route-2"}
           (select-keys (client/get "http://localhost:8080/route-2") [:body :status])))
    (request-routing/stop app)))

(deftest test-bad-order

  (let [app (request-routing/run-bad-order)]
    (is (thrown? Exception
          (client/get "http://localhost:8080/route-1")))
    (request-routing/stop app)))

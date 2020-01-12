(ns packt-clj.exercises.test-hello-world-web-app
  (:require
    [clj-http.client :as client]
    [clojure.test :refer :all]))

(deftest test-hello-world

  (is (= {:status 200
          :body "Hello World"}
         (select-keys (client/get "http://localhost:8080") [:body :status]))))

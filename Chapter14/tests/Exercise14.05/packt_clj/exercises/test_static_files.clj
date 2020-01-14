(ns packt-clj.exercises.test-static-files
  (:require
    [clj-http.client :as client]
    [clojure.java.io :as io]
    [clojure.test :refer :all]
    [packt-clj.exercises.static-files :as static-files]))

(deftest test-static-files
  (let [app (static-files/run)]
    (is (= {:status 200
            :body "This is only a sample\n"}
           (select-keys (client/get "http://localhost:8080/files/sample.txt") [:body :status])))
    (is (= {:status 200
            :body   (subs (slurp (io/resource "match_scores_1991-2016_unindexed_csv.csv")) 0 100)}
           (-> (client/get "http://localhost:8080/files/match_scores_1991-2016_unindexed_csv.csv")
               (select-keys [:body :status])
               (update :body subs 0 100))))
    (.stop app)))

(ns packt-clj.exercises.test-request-body
  (:require
    [clojure.test :refer :all]
    [packt-clj.exercises.request-body :as request-body]))

(deftest test-request-body

  (let [app (request-body/run)]
    (request-body/persist-data-structure-json)
    (is (= {:a 1
            :b [4 3 2]}
          (request-body/retrieve-data-structure-edn)))
    (request-body/persist-data-structure-edn)
    (is (= {:a 1
            :b #{2 3 4}}
           (request-body/retrieve-data-structure-edn)))
    (.stop app)))

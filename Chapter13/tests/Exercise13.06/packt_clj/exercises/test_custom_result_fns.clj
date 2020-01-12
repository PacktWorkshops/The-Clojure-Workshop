(ns packt-clj.exercises.test-custom-result-fns
  (:require
    [clojure.test :refer :all]
    [packt-clj.exercises.custom-result-fns :as custom-result-fns]))

(def raw-sample-result
  {:duration 1226})

(deftest test-add-user-friendly-duration
  (is (= {:duration 1226
          :friendly-duration "20m 26s"}
         (custom-result-fns/add-user-friendly-duration raw-sample-result))))

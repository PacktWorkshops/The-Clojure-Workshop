(ns packt-clj.chapter-5-tests.exercsie-5-08-test
  (:require  [clojure.test :as t :refer [deftest is testing]]
             [packt-clj.chapter-5-tests.exercise-5-08 :as exo]))

(deftest recalculate-rating
  (is (= 1479.52
         (exo/recalculate-rating 1500 0.64 0)))
  (is (= 431.04
        (exo/recalculate-rating 400 0.03 1))))


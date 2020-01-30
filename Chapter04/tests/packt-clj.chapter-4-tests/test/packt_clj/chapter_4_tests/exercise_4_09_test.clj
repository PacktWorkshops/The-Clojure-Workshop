(ns packt-clj.exercise-4-09-test
  (:require  [clojure.test :as t :refer [deftest is testing]]))

(def temperature-by-day
  [18 23 24 23 27 24 22 21 21 20 32 33 30 29 35 28 25 24 28 29 30])

(deftest average-temperature
  (is (= 26
         (let [total (apply + temperature-by-day)
               c (count temperature-by-day)]
           (/ total c)))))


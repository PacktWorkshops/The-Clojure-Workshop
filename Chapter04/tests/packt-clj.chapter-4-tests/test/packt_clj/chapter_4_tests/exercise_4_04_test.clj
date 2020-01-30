(ns packt-clj.exercise-4-04-test
  (:require  [clojure.test :as t :refer [deftest is testing]]
             [packt-clj.chapter-4-tests.exercise-4-04 :as exo]))


(deftest our-range
  (is (= [0 1 2 3 4] (exo/our-range 5)))
    (is (= [0 10 20 30 40]
         (map (fn [i] (print ".") (* i 10)) (exo/our-range 5)))))



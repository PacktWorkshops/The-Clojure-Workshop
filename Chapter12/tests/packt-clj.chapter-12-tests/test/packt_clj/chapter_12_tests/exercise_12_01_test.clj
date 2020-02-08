(ns packt-clj.chapter-12-tests.exercise-12-01-test
  (:require
   [clojure.test :as t :refer [deftest is testing]]
   [packt-clj.chapter-12-tests.exercise-12-01 :as exo]))


(deftest int-count
  (is (= 3 (exo/int-count 1 [94 33 1 23 44 1 1]))))

(deftest identical-output-between-map-and-pmap
  (is (= (map #(exo/int-count % exo/random-ints) [0 1 2 45 788  500  999])
         (pmap #(exo/int-count % exo/random-ints) [0 1 2 45 788  500  999]))))


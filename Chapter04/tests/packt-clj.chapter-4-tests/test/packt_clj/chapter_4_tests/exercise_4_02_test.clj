(ns packt-clj.exercise-4-02-test
  (:require  [clojure.test :as t :refer [deftest is testing]]))

(deftest simple-predicates
  (is (odd? 5))
  (is (not (odd? 6))))


(deftest filtering-and-removing-odd-integers
  (is (= [1 3 5] (filter odd? [1 2 3 4 5])))
  (is (= [2 4] (remove odd? [1 2 3 4 5]))))

(deftest filter-with-constantly
  (is (= [1 2 3 4 5] (filter (constantly true) [1 2 3 4 5])))
  (is (= '() (filter (constantly false)   [1 2 3 4 5]))))

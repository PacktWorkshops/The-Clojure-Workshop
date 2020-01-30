(ns packt-clj.exercise-4-01-test
  (:require  [clojure.test :as t :refer [deftest is testing]]))

(deftest basic-use-of-map
  (is (= [10 20 30 40 50]
         (map (fn [i] (* i 10)) [1 2 3 4 5])))
  (is (= [5 7 4 6 3]
         (map count ["Let's" "measure" "word" "length" "now"]))))




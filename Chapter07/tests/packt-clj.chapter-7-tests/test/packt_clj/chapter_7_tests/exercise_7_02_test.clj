(ns packt-clj.chapter-7-tests.exercise-7-02-test
  (:require [packt-clj.chapter-7-tests.exercise-7-02 :as exo]
            [clojure.test :as t :refer [deftest is testing]]))

(deftest potato-source
  (is (every? integer? (take 5 exo/endless-potatoes))))

(deftest average-potatoes
  (testing "simple case"
    (let [not-random-potatoes [100 100 100 100 100]
          avg (exo/average-potatoes '() not-random-potatoes)]
      (is (= (first avg) [100 1 100]))
      (is (= (last avg) [100 5 500]))))
  (testing "large number of potaotes"
    (let [many-potatoes (take 1000000 exo/endless-potatoes)
          [_ potato-count average] (last (exo/average-potatoes '() many-potatoes))]
      (is (= 1000000 potato-count))
      (is (< (* 10 1000000) average (* 400 1000000))))))






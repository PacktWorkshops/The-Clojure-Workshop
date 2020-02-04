(ns packt-clj.chapter-11-tests.exercise-11-01-test
  (:require [packt-clj.chapter-11-tests.exercise-11-01 :as exo]
            [clojure.test :as t :refer [deftest is testing]]))


(deftest and-ors
  (testing "base case"
    (is (exo/and-ors (= 5 6) (pos? 1)))
    (is (exo/and-ors (= 5 6) (pos? 1) | (= 5 6) (zero? 0)))
    (is (exo/and-ors (= 5 6) (pos? 1) | (= 5 6) (zero? 0) | (= 5 6) (odd? 5)))
    (is (not (exo/and-ors (= 5 6) (pos? 1) | (= 5 6) (zero? 0) | (= 5 6) (even? 5)))))
  (testing "nested ors"
    (is (exo/and-ors (= 5 6) (pos? 1) |
                     (exo/and-ors (= 5 6) (odd? 3) | (= 5 6) (even? 2))))
    (is (not (exo/and-ors (= 5 6) (pos? 1) |
                          (exo/and-ors (= 5 6) (odd? 3) | (= 5 6) (even? 5)))))))


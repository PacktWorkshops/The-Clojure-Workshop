(ns packt-clj.chapter-5-tests.exercise-5-07-test
  (:require [packt-clj.chapter-5-tests.exercise-5-07 :as exo]
            [clojure.test :as t :refer [deftest is testing]]))


(deftest match-probability
  (is (< 0.15
         (exo/match-probability 700 1000)
         0.16))
  (is (< 0.84
         (exo/match-probability 1000 700)
         0.85))
  (is (= 1.0
         (+
           (exo/match-probability 700 1000)
           (exo/match-probability 1000 700))))
  (is (= 1/2
         (exo/match-probability 1000 1000)))
  (is (= 1/10001
         (exo/match-probability 400 2000))))


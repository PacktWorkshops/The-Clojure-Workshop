(ns packt-clj.chapter-6-tests.exercise-6-04-test
  (:require
   [packt-clj.chapter-6-tests.exercise-6-01 :as exo-one]
   [packt-clj.chapter-6-tests.exercise-6-04 :as exo]
   [clojure.test :as t :refer [deftest is testing]]))


(deftest looping-robust-bag-sequences
  (is (every? vector (exo/looping-robust-bag-sequences (exo-one/article-stream 10000)))))


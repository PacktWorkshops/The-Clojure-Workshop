(ns packt-clj.chapter-6-tests.exercise-6-03-test
  (:require
   [packt-clj.chapter-6-tests.exercise-6-01 :as exo-one]
   [packt-clj.chapter-6-tests.exercise-6-03 :as exo]
   [clojure.test :as t :refer [deftest is testing]]))



(deftest robust-bag-sequences
  (let [million-articles (exo/robust-bag-sequences (exo-one/article-stream 1000000))]
    (is (every? vector million-articles))))


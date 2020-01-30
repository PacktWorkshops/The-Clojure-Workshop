(ns packt-clj.exercise-4-05-test
  (:require
   [clojure.test :as t :refer [deftest is testing]]
   [packt-clj.chapter-4-tests.exercise-4-05 :as exo]))

(deftest some-random-integers
  (is (= 5 (count (exo/some-random-integers 5))))
  (is (= 12 (count (exo/some-random-integers 12)))))

(deftest our-randoms
  (let [our-randoms (repeatedly (partial rand-int 100))]
    (is (= 20 (count (take 20 our-randoms))))))

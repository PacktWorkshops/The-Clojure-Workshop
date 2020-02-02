(ns packt-clj.chapter-6-tests.exercise-6-06-test
  (:require
   [packt-clj.chapter-6-tests.exercise-6-06 :as exo]
   [clojure.test :as t :refer [deftest is testing]]))




(deftest find-path
  (testing "one and two city routes"
    (is (= [:sevilla]
           (exo/find-path* exo/lookup :sevilla [:sevilla])))
    (is (= [:sevilla]
           (exo/find-path exo/lookup :sevilla :sevilla)))
    (is (= [:sevilla :madrid]
           (exo/find-path* exo/lookup :madrid [:sevilla]))))

  (testing "with small maps"
    (is (= [[:paris :milan :rome] [:paris :geneva :rome]]
           (exo/find-path* exo/small-routes :rome [:paris])))
    (is (= [[:paris :milan :rome]
            [:paris :geneva :rome]
            [:paris :barcelona :milan :rome]]
           (exo/find-path* exo/more-routes :rome [:paris]))))


  (testing "long paths with complete rail map"
    (let [complete-paths (exo/find-path* exo/lookup :rome [:paris])]
      (is (some #(= [:paris :frankfurt :milan :rome] %) complete-paths) "Shortest path")
      (is (every? #(= :paris (first %)) complete-paths) "All paths begin in Paris")
      (is (every? #(= :rome (last %)) complete-paths) "All roads lead to Rome"))))






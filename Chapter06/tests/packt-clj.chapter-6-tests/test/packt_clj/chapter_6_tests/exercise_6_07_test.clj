(ns packt-clj.chapter-6-tests.exercise-6-07-test
  (:require
   [clojure.test :as t :refer [deftest is testing]]
   [packt-clj.chapter-6-tests.exercise-6-07 :as exo]))


(deftest cost-of-route
  (is (= 603
         (exo/cost-of-route exo/lookup [:london :paris :amsterdam :berlin :warsaw])))
  (is (= 0
         (exo/cost-of-route exo/lookup [:london]))))


(deftest min-route
  (is (= {:cost 79 :best [:milan :vienna]}
         (exo/min-route exo/lookup [[:milan :vienna]
                                    [:milan :frankfurt :prague :vienna]
                                    [:milan :geneva :paris :amsterdam :berlin :warsaw :prague :vienna]]))))

(deftest find-path
  (is (= {:cost 224 :best [:paris :milan :rome]}
         (exo/find-path exo/lookup :paris :rome)))
  (is (= {:cost 291 :best [:paris :frankfurt :berlin]}
         (exo/find-path exo/lookup :paris :berlin)))
  (is (= {:cost 720 :best [:warsaw :prague :vienna :milan :barcelona :sevilla]}
         (exo/find-path exo/lookup :warsaw :sevilla))))

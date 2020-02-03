(ns packt-clj.chapter-7-tests.exercise-7-01-test
  (:require
   [clojure.test :as t :refer [deftest is testing]]
   [packt-clj.chapter-7-tests.exercise-7-01 :as exo]))

(deftest local-max-and-min
  (is (exo/local-max? [[24 100] [26 110] [24 120]]))
  (is (exo/local-min? [[26 100] [24 110] [26 120]]))
  (is (not (exo/local-max? [[24 100] [25 110] [26 120]])))
  (is (not (exo/local-max? [[24 100] [26 110] [26 120]])))
  (is (not (exo/local-max? [[25 100] [25 110] [26 120]]))))


(def sample-data
  [[24.2 420031]
   [25.8 492657]
   [25.9 589014]                        ;max
   [23.8 691995]                        ;min
   [24.7 734902]                        ;max
   [23.2 794243]
   [23.1 836204]                        ;min
   [23.5 884120]])

(deftest inflection-points
  (is (= [[25.9 589014 :peak]
          [23.8 691995 :valley]
          [24.7 734902 :peak]
          [23.1 836204 :valley]]
         (exo/inflection-points sample-data))))

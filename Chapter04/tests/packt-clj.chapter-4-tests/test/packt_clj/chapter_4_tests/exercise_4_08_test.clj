(ns packt-clj.exercise-4-08-test
  (:require  [clojure.test :as t :refer [deftest is testing]]))

(def temperature-by-day
  [18 23 24 23 27 24 22 21 21 20 32 33 30 29 35 28 25 24 28 29 30])

(deftest weather-changes
  (is (= [:warmer          
          :warmer
          :colder
          :warmer
          :colder
          :colder
          :colder
          :unchanged
          :colder
          :warmer
          :warmer
          :colder
          :colder
          :warmer
          :colder
          :colder
          :colder
          :warmer
          :warmer
          :warmer]
         (map (fn [today yesterday] 
                (cond (> today yesterday) :warmer
                      (< today yesterday) :colder
                      (= today yesterday) :unchanged))
              (rest temperature-by-day)
              temperature-by-day))))


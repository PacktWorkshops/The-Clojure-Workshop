(ns packt-clj.chapter-5-tests.exercise-5-01-test
  (:require
   [clojure.test :as t :refer [deftest is testing]]))


(def weather-days
  [{:max 31
    :min 27
    :description :sunny
    :date "2019-09-24"}
   {:max 28
    :min 25
    :description :cloudy
    :date "2019-09-25"}
   {:max 22
    :min 18
    :description :rainy
    :date "2019-09-26"}
   {:max 23
    :min 16
    :description :stormy
    :date "2019-09-27"}
   {:max 35
    :min 19
    :description :sunny
    :date "2019-09-28"}])


(deftest max-with-apply
  (is (= 35 (apply max (map :max weather-days)))))

(deftest max-with-reduce
  (is (= {:max 35
          :min 19
          :description :sunny
          :date "2019-09-28"}
         (reduce (fn [max-day-so-far this-day]
                   (if (> (:max this-day) (:max max-day-so-far))
                     this-day
                     max-day-so-far))
                 weather-days))))


(deftest min-with-reduce
  (is (= {:max 22
          :min 18
          :description :rainy
          :date "2019-09-26"}
         (reduce (fn [min-max-day-so-far this-day]
                   (if (< (:max this-day) (:max min-max-day-so-far))
                     this-day
                     min-max-day-so-far))
                 weather-days))))

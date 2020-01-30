(ns packt-clj.chapter-4-tests.exercise-4-04
  (:require  [clojure.test :as t]))

(defn our-range [limit]
  (take-while #(< % limit) (iterate inc 0)))

(def by-ten (map (fn [i] (print ".") (* i 10)) (our-range 5)))

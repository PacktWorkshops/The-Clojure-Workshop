(ns packt-clj.chapter-7-tests.exercise-7-04
  (:require
   [clojure.data.csv :as csv]
   [clojure.java.io :as io]
   [clojure.math.numeric-tower :as math]
   [semantic-csv.core :as sc]))


(defn take-matches [limit tree]
  (cond (zero? limit)
        '()
        (= 1 limit)
        (first tree)
        :else
        (cons
         (first tree)
         (cons
          [(take-matches (dec limit) (first (second tree)))
           (take-matches (dec limit) (second (second tree)))]
          '()))))



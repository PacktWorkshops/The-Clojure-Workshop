(ns packt-clj.chapter-5-tests.exercise-5-08
  (:require 
            [clojure.java.io :as io]
            [clojure.data.csv :as csv]
            [semantic-csv.core :as sc]
            [clojure.math.numeric-tower :as math]))

(def k-factor 32)

(defn recalculate-rating [previous-rating expected-outcome real-outcome]
        (+ previous-rating (* k-factor (- real-outcome expected-outcome))))



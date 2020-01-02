(ns coffee-app.utils-test-check
    (:require [coffee-app.utils :refer :all]
      [clojure.test.check :as tc]
      [clojure.test.check.generators :as gen]
      [clojure.test.check.properties :as prop]
      [clojure.test.check.clojure-test :refer [defspec]]))

(defspec display-order-test-check 1000
         (prop/for-all [order (gen/fmap (fn [[number type price]]
                                            {:number number
                                             :type type
                                             :price price})
                                        (gen/tuple (gen/large-integer* {:min 0})
                                                   gen/keyword
                                                   (gen/double* {:min 0.1 :max 999 :infinite? false :NaN? false} )))]
                       (= (str "Bought " (:number order) " cups of " (name (:type order)) " for €" (:price order)) (display-order order))))

(defspec file-exists-test-check 1000
         (prop/for-all [file gen/string-alphanumeric]
                       (false? (file-exists file))))

(defspec load-orders-test-check 1000
         (prop/for-all [file gen/string-alphanumeric]
                       (vector? (load-orders file))))
(ns coffee-app.utils-test
    (:require
      [clojure.test.check.generators :as gen]
      [clojure.test.check.properties :as prop]
      [clojure.test.check.clojure-test :refer [defspec]]
      [coffee-app.core :refer [price-menu]]
      [coffee-app.utils :refer :all]))

(defspec coffee-price-test-check 1000
         (prop/for-all [int gen/nat]
                       (= (float (* int (:latte price-menu))) (calculate-coffee-price price-menu :latte int))))

(defspec coffee-price-test-check-all-params 1000
         (prop/for-all [int (gen/fmap inc gen/nat)
                        price-hash (gen/map gen/keyword
                                            (gen/double* {:min 0.1 :max 999 :infinite? false :NaN? false} ) {:min-elements 2})]
                       (let [coffee-tuple (first price-hash)]
                            (= (float (* int (second coffee-tuple))) (calculate-coffee-price price-hash (first coffee-tuple) int)))))
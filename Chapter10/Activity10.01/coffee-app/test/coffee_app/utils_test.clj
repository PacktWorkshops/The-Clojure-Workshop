(ns coffee-app.utils-test
    (:require [clojure.test :refer :all]
              [coffee-app.core :refer [price-menu]]
              [coffee-app.utils :refer :all]))

;(defn- create-coffee-file []
;       (with-open [w (clojure.java.io/writer "orders.edn")]
;                  (binding [*out* w]
;                           (pr {:type :mocha, :number 3, :price 1.2}))))
;
;(defn fixtures-create-file [f]
;      (create-coffee-file)
;      (f)
;      (clojure.java.io/delete-file "orders.edn"))
;
;(use-fixtures :once fixtures-create-file)
;; tests using thrown? from clojure.test
;(println (gen/sample gen/small-integer 100))

;;; Testing calculate-coffee-price

(deftest calculate-coffee-price-test-with-single-is
         (testing "Single test with is macro."
                  (is (= (calculate-coffee-price price-menu :latte 1) 0.5))))

(deftest calculate-coffee-price-test-with-multiple-is
         (testing "Multiple tests with is macro."
                  (is (= (calculate-coffee-price price-menu :latte 1) 0.5))
                  (is (= (calculate-coffee-price price-menu :latte 2) 1.0))
                  (is (= (calculate-coffee-price price-menu :latte 3) 1.5))))

(deftest calculate-coffee-price-test-with-are
         (testing "Multiple tests with are macro"
                  (are [coffees-hash coffee-type number-of-cups result]
                       (= (calculate-coffee-price coffees-hash coffee-type number-of-cups) result)
                       price-menu :latte 1 0.5
                       price-menu :latte 2 1.0
                       price-menu :latte 3 1.5)))

(deftest calculate-coffee-price-throws-class-cast-error
         (testing "Number of coffee cups needs to be a number"
                  (is (thrown? ClassCastException (calculate-coffee-price price-menu :latte "1")))))

(deftest display-order-test
         (testing "Multiple tests with is macro"
                  (is (= (display-order {:number 4 :price 3.8 :type :latte}) "Bought 4 cups of latte for €3.8"))
                  (is (= (display-order {:number 7 :price 6.3 :type :espresso}) "Bought 7 cups of espresso for €6.3")))
         (testing "Multiple tests with are macro"
                  (are [order result]
                       (= (display-order order) result)
                       {:number 2 :price 1.5 :type :latte} "Bought 2 cups of latte for €1.5"
                       {:number 3 :price 6.3 :type :mocca} "Bought 3 cups of mocca for €6.3"
                       {:number 8 :price 10 :type :espresso} "Bought 8 cups of espresso for €10")))

(deftest file-exists-test
         (testing "File does exist"
                  (testing "Multiple tests with is macro"
                           (is (file-exists "/etc"))
                           (is (file-exists "/lib")))
                  (testing "Multiple tests with are macro"
                           (are [file] (true? (file-exists file))
                                "/etc"
                                "/var"
                                "/tmp")))
         (testing "File does not exist"
                  (testing "Multiple tests with is macro"
                           (is (false? (file-exists "no-file")))
                           (is (false? (file-exists "missing-file"))))
                  (testing "Multiple tests with are macro"
                           (are [file] (false? (file-exists file))
                                "eettcc"
                                "tmp-tmp"
                                "no-file-here"))))

(defn uuid [] (str (java.util.UUID/randomUUID)))

(deftest can-save-and-load-some-data
         (testing "saving and loading"
                  (let [test-file (str "/tmp/" (uuid) ".edn")
                        test-data {:number 1 :type :latte}]
                       (save-to test-file test-data)
                       (is (= (list test-data) (load-orders test-file))))))

(deftest loads-empty-vector-from-not-existing-file
         (testing "saving and loading"
                  (let [test-file (str "/tmp/" (uuid) ".edn")]
                       (is (= [] (load-orders test-file))))))

(deftest saves-coffee-order
         (testing "Saves cofee order"
                  (let [test-file (str "/tmp/" (uuid) ".edn")
                        test-data {:type :latte, :number 2, :price 2.6}]
                       (save-coffee-order test-file :latte 2 2.6)
                       (is (= (list test-data) (load-orders test-file))))))
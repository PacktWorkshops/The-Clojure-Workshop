(ns books-app.utils-test
    (:require [clojure.test :refer :all]
              [books-app.core :refer [books]]
              [books-app.utils :refer :all]))

(deftest calculate-books-price-test-with-single-is
         (testing "Single test with is macro."
                  (is (= (calculate-book-price (get books :2019) :clojure 1) 20.0))))

(deftest calculate-books-price-test-with-multiple-is
         (testing "Multiple tests with is macro."
                  (is (= (calculate-book-price (get books :2019) :clojure 1) 20.0))
                  (is (= (calculate-book-price (get books :2019) :go 2) 36.0))
                  (is (= (calculate-book-price (get books :2018) :clojure 3) 45.0))))

(deftest calculate-books-price-test-with-are
         (testing "Multiple tests with are macro"
                  (are [books-hash book-type number-of-cups result]
                       (= (calculate-book-price books-hash book-type number-of-cups) result)
                       (get books :2019) :clojure 2 40.0
                       (get books :2019) :go 1 18.0
                       (get books :2018) :clojure 3 45.0)))

(deftest calculate-book-price-throws-class-cast-error
         (testing "Number of books needs to be a number"
                  (is (thrown? ClassCastException (calculate-book-price (get books :2019) :clojure "1")))))

(deftest display-order-test
         (testing "Multiple tests with is macro"
                  (is (= (display-order {:year :2019, :prog-lang :go, :number 2, :price 40.0} books)
                         "Bought 2: Go Cookbook published in 2019 for €40.0"))
                  (is (= (display-order {:year :2019, :prog-lang :clojure, :number 3, :price 56.0} books)
                         "Bought 3: Hands-On Reactive Programming with Clojure published in 2019 for €56.0")))
         (testing "Multiple tests with are macro"
                  (are [order result]
                       (= (display-order order books) result)
                       {:year :2019, :prog-lang :go, :number 2, :price 40.0}
                       "Bought 2: Go Cookbook published in 2019 for €40.0"
                       {:year :2019, :prog-lang :clojure, :number 4, :price 80.0}
                       "Bought 4: Hands-On Reactive Programming with Clojure published in 2019 for €80.0"
                       {:year :2018, :prog-lang :go, :number 1, :price 16.5}
                       "Bought 1: Advanced Go programming published in 2018 for €16.5"))
         )

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

(deftest saves-books-order
         (testing "Saves books order"
                  (let [test-file (str "/tmp/" (uuid) ".edn")
                        expected-data {:year :2019, :prog-lang :clojure, :number 2, :price 40.0}]
                       (save-book-order test-file :2019 :clojure 2 40.0)
                       (is (= (list expected-data) (load-orders test-file))))))
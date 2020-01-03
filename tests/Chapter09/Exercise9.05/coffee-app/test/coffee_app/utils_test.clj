(ns coffee-app.utils-test
    (:require [clojure.test :refer [are is deftest testing]]
              [coffee-app.core :refer [price-menu]]
              [coffee-app.utils :refer :all]))

(deftest file-exists-test
         (testing "File does exist"
                  (testing "Multiple tests with is macro"
                           (is (file-exists? "/etc"))
                           (is (file-exists? "/lib")))
                  (testing "Multiple tests with are macro"
                           (are [file] (true? (file-exists? file))
                                "/etc"
                                "/var"
                                "/tmp")))
         (testing "File does not exist"
                  (testing "Multiple tests with is macro"
                           (is (false? (file-exists? "no-file")))
                           (is (false? (file-exists? "missing-file"))))
                  (testing "Multiple tests with are macro"
                           (are [file] (false? (file-exists? file))
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
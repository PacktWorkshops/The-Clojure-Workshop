(ns hello-test.core-test
  (:require [cljs.test :refer-macros [are async deftest is testing]]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :refer-macros [for-all]]
            [clojure.test.check.clojure-test :refer-macros [defspec]]
            [cuerdas.core :as str]
            [hello-test.core :refer [profanity-filter http-get prefix-digit-remover]]))

(deftest capitalize-test-is
         (testing "Test capitalize? function using is macro"
                  (is (= "Katy" (str/capitalize "katy")))
                  (is (= "John" (str/capital "john")))
                  (is (= "Mike" (str/capitalize "mike")))))

(deftest capitalize-test-are
         (testing "Test capitalize? function using are macro"
                 (are [name-capitalized name] (= name-capitalized (str/capitalize name))
                      "Amanda" "amanda"
                      "Rob" "rob"
                      "Dan" "dan")))

(deftest profanity-filter-test
         (testing "Filter replaced bad word"
                  (is (= "Clojure is great" (profanity-filter "Clojure is bad"))))
         (testing "Filter does not replace good words"
                  (are [string result] (= result (profanity-filter string))
                       "Clojure is great" "Clojure is great"
                       "Clojure is brilliant" "Clojure is brilliant")))

(deftest error-thrown-test
         (testing "Catching errors in ClojureScript"
                  (is (thrown? js/Error (assoc ["dog" "cat" "parrot"] 4 "apple")))))

(deftest http-get-test
         (async done
                (http-get "https://api.github.com/users" {:with-credentials? false
                                                          :query-params      {"since" 135}}
                          (fn [response]
                              (is (= 200 (:status response)))
                              (done)))))

(defspec simple-test-check 1000
         (for-all [some-string gen/string-ascii]
                  (= (str/replace some-string "bad" "great") (profanity-filter some-string))))

(defspec prefix-digit-remover-test 1000
         (for-all [some-string gen/string-ascii]
                  (=
                    ;(str/replace-first some-string "1" "a") ;;; will result in failing test. An example of failing test
                    (if (str/starts-with? some-string "1") (str/replace-first some-string "1" "a") some-string)
                    (prefix-digit-remover some-string))))
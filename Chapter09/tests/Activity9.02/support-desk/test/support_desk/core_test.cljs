(ns support-desk.core-test
  (:require
    [cljs.test :refer-macros [are deftest is testing use-fixtures]]
    [clojure.test.check.generators :as gen]
    [clojure.test.check.properties :refer-macros [for-all]]
    [clojure.test.check.clojure-test :refer-macros [defspec]]
    [cuerdas.core :as str]
    [support-desk.utils :refer [delete-item-from-list-by-title get-priorities-list get-sort-message get-sorted-priorities-list handle-sort-finish]]))



(use-fixtures :each
              {:before (fn [] (do
                                (def priorities-list [{:title "IE bugs" :priority 2} {:title "404 page" :priority 1} {:title "Forgotten username" :priority 2}
                                                      {:title "Login token" :priority 1} {:title "Mobile version" :priority 3} {:title "Load time" :priority 5}])
                                (def app-state (atom {:sort-counter 0}))))})

(deftest get-sort-message-test
         (testing "Using is macro"
                  (is (= "little (1)" (get-sort-message 1)))
                  (is (= "medium (4)" (get-sort-message 4)))
                  (is (= "many (8)" (get-sort-message 8))))
         (testing "Using are macro"
                  (are [result number] (= result (get-sort-message number))
                       "little (1)" 1
                       "little (2)" 2
                       "medium (3)" 3
                       "medium (4)" 4
                       "medium (5)" 5
                       "many (6)" 6)))

(defspec get-sort-message-test-check 10
         (for-all [count gen/nat]
                  (= (str/format "%s (%s)"
                                 (cond
                                   (< count 3) "little"
                                   (< count 6) "medium"
                                   :else "many")
                                 count)
                     (get-sort-message count))))

(deftest get-priorities-list-test
         (testing "Testing filtering priorities based on priority number"
                  (is (= []
                         (get-priorities-list priorities-list 0)))
                  (is (= [{:title "404 page", :priority 1} {:title "Login token", :priority 1}]
                         (get-priorities-list priorities-list 1)))
                  (is (= [{:title "IE bugs", :priority 2}
                          {:title "404 page", :priority 1}
                          {:title "Forgotten username", :priority 2}
                          {:title "Login token", :priority 1}]
                         (get-priorities-list priorities-list 2)))
                  (is (=
                        [{:title "IE bugs", :priority 2}
                         {:title "404 page", :priority 1}
                         {:title "Forgotten username", :priority 2}
                         {:title "Login token", :priority 1}
                         {:title "Mobile version", :priority 3}]
                        (get-priorities-list priorities-list 3)))))

(deftest get-sorted-priorities-list-test
         (testing "Sorting priorities list"
                  (is (= [{:title "404 page", :priority 1}
                          {:title "Login token", :priority 1}
                          {:title "IE bugs", :priority 2}
                          {:title "Forgotten username", :priority 2}
                          {:title "Mobile version", :priority 3}
                          {:title "Load time", :priority 5}]
                         (get-sorted-priorities-list priorities-list)))))

(deftest delete-item-from-list-by-title-test
         (testing "Passing empty list"
                  (is (= []
                         (delete-item-from-list-by-title "Login token" [])))
                  (is (= []
                         (delete-item-from-list-by-title "Login token" nil))))
         (testing "Passing valid list"
                  (is (= (delete-item-from-list-by-title "Login token" priorities-list)))))

(deftest handle-sort-finish-test
         (testing "Calling fn once"
                  (is (= {:sort-counter 1}
                         ((handle-sort-finish app-state) "event" "object"))))
         (testing "Calling fn twice"
                  (is (= {:sort-counter 2}
                         ((handle-sort-finish app-state) "event" "object")))))
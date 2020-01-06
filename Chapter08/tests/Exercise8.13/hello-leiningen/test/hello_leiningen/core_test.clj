(ns hello-leiningen.core-test
  (:require [clojure.test :refer :all]
            [java-time :as time]
            [hello-leiningen.core :refer :all]))

(deftest java-time-test
  (testing "Testing Java Time"
    (is (= java.time.LocalTime (class (time/local-time))))))

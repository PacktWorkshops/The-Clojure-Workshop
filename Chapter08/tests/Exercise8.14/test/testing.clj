(ns testing
    (:require [clojure.test :refer [are deftest]]
      [clojure.contrib.humanize :refer [numberword duration]]))

(deftest number-to-word-testing
         (are [expected actual] (= expected actual)
              "four thousand five hundred and eighty-nine" (numberword 4589)
              "nine hundred and sixty-two" (numberword 962)))

(deftest duration-test
         (are [expected actual] (= expected actual)
              "less than a second" (duration 500)
              "one second" (duration 1000)
              "one minute" (duration 60000)))
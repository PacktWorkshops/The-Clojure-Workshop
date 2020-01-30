(ns packt-clj.exercise-4-03-test
  (:require  [clojure.test :as t :refer [deftest is testing]]))

(deftest partition-student-list
  (let [students [{:name "Eliza" :year 1994}
                  {:name "Salma" :year 1995}
                  {:name "Jodie" :year 1997}
                  {:name "Kaitlyn" :year 2000}
                  {:name "Alice" :year 2001}
                  {:name "Pippa" :year 2002}
                  {:name "Fleur" :year 2002}]]
    (is (= [{:name "Eliza" :year 1994}
            {:name "Salma" :year 1995}
            {:name "Jodie" :year 1997}]
           (take-while #(< (:year %) 2000) students)))
    (is (= [{:name "Kaitlyn" :year 2000}
            {:name "Alice" :year 2001}
            {:name "Pippa" :year 2002}
            {:name "Fleur" :year 2002}]
          (drop-while #(< (:year %) 2000) students)))))

(ns packt-clj.chapter-6-tests.exercise-6-01-test
  (:require
   [clojure.test :as t :refer [deftest is testing]]
   [packt-clj.chapter-6-tests.exercise-6-01 :as exo]))

(deftest article-stream
  (is 12 (= (count (exo/article-stream 12))))
  (is (every? (comp integer? :weight) (exo/article-stream 12)))
  (is (every? (comp integer? :max-dimension) (exo/article-stream 12)))
  (is (every? (comp string? :name) (exo/article-stream 12))))


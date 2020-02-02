(ns packt-clj.chapter-6-tests.exercise-6-02-test
  (:require
   [clojure.test :as t :refer [deftest is testing]]
   [packt-clj.chapter-6-tests.exercise-6-01 :as exo-one]
   [packt-clj.chapter-6-tests.exercise-6-02 :as exo]))

(deftest full-bag?-predicate
  (is (not (exo/full-bag? [])) "Empty bag is not full")
  (is (not (exo/full-bag? [{:weight 100 :max-dimension 100} {:weight 200 :max-dimension 200}])))
  (is (exo/full-bag?
        [{:weight 3000 :max-dimension 100} {:weight 2000 :max-dimension 200}])
      "Weight causes bag to be full")
  (is (exo/full-bag?
        [{:weight 100 :max-dimension 500} {:weight 200 :max-dimension 400}])
      "Size causes bag to be full"))


(deftest bag-sequences
  (testing "with random items"
    (let [bags (exo/bag-sequences (exo-one/article-stream 12))]
      (is (= 12 (apply + (map count bags))) "The article count should not change")
      (is (every? (comp pos? count) bags) "All the bags should have at least one item")))
  (testing "with fixed items"
    (let [items [{:name "Olive oil", :weight 400, :max-dimension 280}
                 {:name "Potatoes", :weight 2500, :max-dimension 340}
                 {:name "Potatoes", :weight 2500, :max-dimension 340}
                 {:name "Flour", :weight 1000, :max-dimension 140}
                 {:name "Ice cream", :weight 450, :max-dimension 200}
                 {:name "Pepper", :weight 85, :max-dimension 90}
                 {:name "Green beans", :weight 300, :max-dimension 120}
                 {:name "Pepper", :weight 85, :max-dimension 90}
                 {:name "Green beans", :weight 300, :max-dimension 120}
                 {:name "Potatoes", :weight 2500, :max-dimension 340}
                 {:name "Potatoes", :weight 2500, :max-dimension 340}
                 {:name "Olive oil", :weight 400, :max-dimension 280}]
          bags (exo/bag-sequences items)]
      (is (= (first bags)
             [{:name "Olive oil", :weight 400, :max-dimension 280}
              {:name "Potatoes", :weight 2500, :max-dimension 340}]))
      (is (= (second bags)
             [{:name "Potatoes", :weight 2500, :max-dimension 340}])))))

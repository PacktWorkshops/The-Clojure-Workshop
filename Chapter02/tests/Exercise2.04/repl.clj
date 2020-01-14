(require '[clojure.test :as test :refer [is are deftest run-tests]])

(def fibonacci [0 1 1 2 3 5 8])

(deftest exercise-204-test
  (is (= :a (get [:a :b :c] 0)))
  (is (= :c (get [:a :b :c] 2)))
  (is (= nil (get [:a :b :c] 10)))
  (is (= 8 (get fibonacci 6)))
  (is (= 8 (fibonacci 6)))
  (is (= [0 1 1 2 3 5 8 13 21] (conj fibonacci 13 21)))
  (is (= [0 1 1 2 3 5 8 13]
         (let [size (count fibonacci)
               last-number (last fibonacci)
               second-to-last-number (fibonacci (- size 2))]
            (conj fibonacci (+ last-number second-to-last-number))))))

(run-tests)

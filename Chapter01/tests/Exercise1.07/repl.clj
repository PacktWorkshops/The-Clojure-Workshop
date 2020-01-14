(require '[clojure.test :as test :refer [is are deftest run-tests]])
(deftest exercise-107
  (is (= 1 1))
  (is (= 1 1 1))
  (is (= false (= 1 1 1 -1)))

  (is (= nil nil))
  (is (= false (= false nil)))
  (is (= "hello" "hello" (clojure.string/reverse "olleh")))
  (is (= [1 2 3] [1 2 3]))
  (is (= `(1 2 3) [1 2 3]))
  (is (= 1))
  (is (= "I will not reason and compare: my business is to create."))

  (is (< 1 2))
  (is (< 1 10 100 1000))
  (is (= false (< 1 10 10 100)))
  (is (= false (< 3 2 3)))
  (is (< -1 0 1))

  (is (<= 1 10 10 100))
  (is (<= 1 1 1))
  (is (<= 1 2 3))

  (is (> 3 2 1))
  (is (= false (> 3 2 2)))
  (is (>= 3 2 2))

  (is (= false (not true)))
  (is (= true (not nil)))
  (is (= false (not (< 1 2))))
  (is (= false (not (= 1 1))))

  (is (= nil (let [x 50] (println (if (or (<= 1 x 100) (= 0 (mod x 100))) "Valid" "Invalid"))))))

(require '[clojure.test :as test :refer [is are deftest run-tests]])

(deftest is42-test
 (is (= 42 (* (+ 1 2 3) (- 10 3)))))

(run-tests)

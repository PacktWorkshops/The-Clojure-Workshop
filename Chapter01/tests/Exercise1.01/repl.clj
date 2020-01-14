(require '[clojure.test :as test :refer [are deftest run-tests]])

(deftest exercise101-test
  (are 
    [x y] (= x y)
    3 (+ 1 2)
    6 (+ 1 2 3)
    1 (- 3 2)
    12 (* 3 4 1)
    3 (/ 9 3)
    nil (println "Would you like to dance?")
    nil (println (+ 1 2))
    6 (* 2 (+ 1 2))))

(run-tests)

(require '[clojure.test :as test :refer [is are deftest run-tests]])

(deftest exercise103-test
  (is (= "Yes" (if true "Yes" "No")))
  (is (not (= 7 (if false (+ 3 4) (rand)))))
  (is (= 2 (do (* 3 4) (/ 8 4) (+ 1 1))))
  (is (= "And the last is returned" (when true (println "First argument") (println "Second argument") "And the last is returned"))))

(run-tests)

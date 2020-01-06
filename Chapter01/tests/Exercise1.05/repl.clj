(require '[clojure.test :as test :refer [is are deftest run-tests]])
(require '[clojure.repl :refer [doc]])

(load-file "../../Exercise1.05/repl.clj")

(deftest exercise105-test
  (is (= 4 ((fn [x] (* x x)) 2)))
  (is (= 4 (square 2)))
  (is (= 100 (square 10)))
  (is (= "In calmness lies true pleasure" (meditate "in calmness lies true pleasure" true)))
  (is (= "IN CALMNESS LIES TRUE PLEASURE!" (meditate "in calmness lies true pleasure" false)))
  (is (= nil (doc square))))

(run-tests)

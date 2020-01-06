(require '[clojure.test :as test :refer [is are deftest run-tests]])

(load-file "../../Exercise3.05/repl.clj")

(deftest exercise-305-test
  (is (= {:name "Lea", :health 200, :position {:x 10, :y 11, :facing :north}} (move player)))
  (is (= {:position {:x 11, :y 10, :facing :west}} (move {:position {:x 10 :y 10 :facing :west}})))
  (is (= {:position {:x 10, :y 9, :facing :south}} (move {:position {:x 10 :y 10 :facing :south}})))
  (is (= {:position {:x 9, :y 10, :facing :east}} (move {:position {:x 10 :y 10 :facing :east}})))
  (is (= {:position {:x 10, :y 10, :facing :wall}} (move {:position {:x 10 :y 10 :facing :wall}}))))

(run-tests)

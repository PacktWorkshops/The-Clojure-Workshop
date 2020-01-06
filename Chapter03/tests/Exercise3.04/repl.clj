(require '[clojure.test :as test :refer [is are deftest run-tests]])

(load-file "../../Exercise3.04/repl.clj")

(deftest exercise-304-test
  (is (= {:name "Arnold", :health 250} (strike enemy :sweet-potato)))
  (is (= {:name "Arnold", :health 150} (strike enemy :sword)))
  (is (>= 150 (:health (strike enemy :cast-iron-saucepan))))
  (is (>= 100 (:health (mighty-strike enemy)))))

(run-tests)

(require '[clojure.test :as test :refer [is are deftest run-tests]])

(load-file "../../Exercise3.03/repl.clj")

(deftest exercise-303-test
  (is (= {:name "Zulkaz", :health 248.0, :armor 0.8, :camp :trolls} (strike enemy)))
  (is (= {:name "Zulkaz", :health 220.0, :armor 0.8, :camp :trolls} (strike enemy :cast-iron-saucepan)))
  (is (= {:name "Carla", :health 115.0, :camp :gnomes} (strike ally :staff))))

(run-tests)

(require '[clojure.test :as test :refer [is are deftest run-tests]])

(def supported-currencies #{"Dollar" "Japanese yen" "Euro" "Indian rupee" "British pound"})

(deftest exercise-203-test
  (is (= "Dollar" (get supported-currencies "Dollar")))
  (is (= nil (get supported-currencies "Swiss franc")))
  (is (= true (contains? supported-currencies "Dollar")))
  (is (= false (contains? supported-currencies "Swiss franc")))
  (is (= nil (supported-currencies "Swiss franc")))
  (is (= #{"Japanese yen" "Euro" "Dollar" "Monopoly Money" "Indian rupee" "British pound"} (conj supported-currencies "Monopoly Money")))
  (is (= #{"Japanese yen" "Euro" "Dollar" "Monopoly Money" "Indian rupee" "Gold dragon" "British pound" "Gil"} (conj supported-currencies "Monopoly Money" "Gold dragon" "Gil")))
  (is (= #{"Japanese yen" "Euro" "Indian rupee"} (disj supported-currencies "Dollar" "British pound"))))

(run-tests)

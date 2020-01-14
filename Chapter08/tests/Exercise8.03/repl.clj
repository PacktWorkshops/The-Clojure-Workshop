(in-ns 'new-namespace)

(def fruits ["orange" "apple" "melon"])

(clojure.core/refer 'clojure.test :only '(deftest is run-tests))

(deftest fruits-test
         (is (clojure.core/= fruits
                ["orange" "apple" "melon"])))

(run-tests)
(in-ns 'new-namespace)

(def fruits ["orange" "apple" "melon"])

(in-ns 'other-namespace)

(clojure.core/refer 'clojure.test :only '(deftest is run-tests))

(deftest fruits-testing
         (is (clojure.core/= ["orange" "apple" "melon"]
                             new-namespace/fruits)))

(run-tests)
(in-ns 'garden)

(def vegetables ["cucumber" "carrot"])

(def fruits ["orange" "apple" "melon"])

(in-ns 'shop)

(clojure.core/refer 'garden :only '(vegetables))

(clojure.core/refer 'clojure.test :only '(deftest is run-tests))

(clojure.core/refer 'clojure.core)

(deftest vegetables-test
         (is (= vegetables
                ["cucumber" "carrot"])))

(deftest fruits-test
         (is (= garden/fruits
                ["orange" "apple" "melon"])))

(run-tests)
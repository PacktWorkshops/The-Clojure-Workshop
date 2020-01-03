(in-ns 'garden)

(def vegetables ["cucumber" "carrot"])

(def fruits ["orange" "apple" "melon"])

(in-ns 'market)

(clojure.core/refer 'garden :exclude '(vegetables))

(clojure.core/refer 'clojure.test :only '(deftest is run-tests))

(clojure.core/refer 'clojure.core)

(deftest fruits-test
         (is (= fruits
                ["orange" "apple" "melon"])))

(deftest vegetables-test
         (is (= garden/vegetables
                ["cucumber" "carrot"])))

(run-tests)
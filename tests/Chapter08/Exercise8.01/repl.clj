(clojure.core/refer 'clojure.test :only '(deftest is run-tests))

(deftest adding-test
         (is (= 3 (+ 1 2))))

(deftest filter-test
         (is (= (filter odd? [1 2 3 4 5 6])
                [1 3 5])))

(run-tests)
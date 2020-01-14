(clojure.core/refer 'clojure.test :only '(are deftest is run-tests))

(import 'java.math.BigDecimal)

(deftest big-decimal-class-test
         (is (= java.math.BigDecimal (class (new BigDecimal "100000")))))

(deftest big-decimal-test
         (are [expected actual] (= expected actual)
              100000M (BigDecimal. "100000")
              156000M   (BigDecimal. "156000")))

(run-tests)
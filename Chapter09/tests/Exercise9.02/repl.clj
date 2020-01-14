(clojure.core/refer 'clojure.test :only '(are deftest is run-tests))

(import '[java.math BigDecimal BigInteger])

(import 'java.time.LocalTime 'java.util.Locale)

(deftest big-integer-test
         (are [expected actual] (= expected actual)
              1000000 (BigInteger. "1000000")
              200000 (BigInteger. "200000")))

(deftest big-decimal-test
         (are [expected actual] (= expected actual)
              100000M (BigDecimal. "100000")
              156000M   (BigDecimal. "156000")
              100000.5M (BigDecimal. 100000.5)))

(deftest pl-locale-test
         (is (= java.util.Locale (class (Locale. "pl")))))

(deftest local-time-test
         (is (= 0 (.getHour (LocalTime/MIDNIGHT)))))

(run-tests)
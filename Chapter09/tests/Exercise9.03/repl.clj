(clojure.core/refer 'clojure.test :only '(are deftest is run-tests))

(import '[java.time ZonedDateTime ZoneId])

(deftest zone-offset-id-test
         (are [expected actual] (= expected actual)
              "+01:00" (. (. (ZonedDateTime/of 2020 1 2 8 56 23 36 (ZoneId/of "UTC+1")) getOffset) getId)
              "+01:00" (.. (ZonedDateTime/of 2020 1 2 8 56 23 36 (ZoneId/of "UTC+1")) getOffset getId)))

(deftest append-string-buffer-test
         (is (= (let [string (StringBuffer. "quick")]
                     (.append string " brown")
                     (.append string " fox")
                     (.append string " jumped")
                     (.append string " over")
                     (.append string " the")
                     (.append string " lazy")
                     (.append string " dog")
                     (.toString string))
                "quick brown fox jumped over the lazy dog")))

(deftest doto-string-buffer-test
         (is (= (let [string (StringBuffer. "quick")]
                     (doto string
                           (.append " brown")
                           (.append " fox")
                           (.append " jumped")
                           (.append " over")
                           (.append " the")
                           (.append " lazy")
                           (.append " dog"))
                     (.toString string))
                "quick brown fox jumped over the lazy dog")))

(run-tests)
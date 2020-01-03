(clojure.core/refer 'clojure.test :only '(are deftest is run-tests))

(def capitals ["Berlin" "Oslo" "Warszawa" "Belgrad"])

(def destinations (java.util.ArrayList. capitals))

(deftest collection-class-test
         (are [expected actual] (is (= expected actual))
              clojure.lang.PersistentVector (class capitals)
              java.util.ArrayList (class (java.util.ArrayList. capitals))
              clojure.lang.PersistentVector (class (vec destinations))
              clojure.lang.LazySeq (class (seq destinations))))


(deftest operate-on-array-list-test
         (are [expected actual] (is (= expected actual))
              "Oslo" (.get destinations 1)
              "Belgrad" (.get destinations 3)))

(.add destinations "Vien")

(deftest add-element-to-array-list
         (is "Vien" (.get destinations 4)))

(def fluss {"Germany" "Rhein" "Poland" "Vistula"})

(def rivers (java.util.HashMap. fluss))

(deftest hash-test
         (are [expected actual] (is (= expected actual))
              clojure.lang.PersistentArrayMap (class fluss)
              java.util.HashMap (class rivers)
              clojure.lang.PersistentArrayMap (class (into {} rivers))))

(run-tests)
(require '[clojure.test :as test :refer [are deftest run-tests]])

(deftest exercise102-test
  (are [x y] (= x y)
    11 (inc 10)
    "Iwillbeconcatenated" (str "I" "will" "be" "concatenated")  
    "This works too" (clojure.core/str "This" " works " "too")
    1 (mod 7 3)
    "SHOUT, SHOUT, LET IT ALL OUT" (clojure.string/upper-case "Shout, shout, let it all out")))

(run-tests)

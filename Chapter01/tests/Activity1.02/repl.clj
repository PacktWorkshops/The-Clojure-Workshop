(require '[clojure.test :as test :refer [are deftest run-tests]])

(load-file "../../Activity1.02/repl.clj")

(deftest co2-estimate-test
 (are 
  [x y] (= x y)
  382 (co2-estimate 2006)
  410 (co2-estimate 2020)
  470 (co2-estimate 2050)))

(run-tests)

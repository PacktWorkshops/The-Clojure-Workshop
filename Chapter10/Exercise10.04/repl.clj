(require '[clojure.test.check :as tc]
         '[clojure.test.check.generators :as gen]
         '[clojure.test.check.properties :as prop])

(gen/sample gen/nat)
;;; (0 1 -1 1 1 2 6 4 -5 -9)

(gen/sample (gen/fmap inc gen/nat))
;;; (1 2 1 -1 -3 4 -5 -1 7 -6)

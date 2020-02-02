(ns packt-clj.chapter-6-tests.exercise-6-05-test
  (:require
   [clojure.test :as t :refer [deftest is testing]]
   [packt-clj.chapter-6-tests.exercise-6-05 :as exo]))


(deftest route-list->distance
  (is (= {:milan 129, :frankfurt 121}
         (exo/route-list->distance-map [[:paris :milan 129]
                                        [:paris :frankfurt 121]]))))

(deftest grouped-routes
  (is (= {:london 236,
          :frankfurt 121,
          :milan 129,
          :madrid 314,
          :geneva 123,
          :amsterdam 139}
         (:paris (exo/grouped-routes exo/routes)))))

(deftest lookup
  (let [lookup (exo/grouped-routes exo/routes)]
    (is (= 314 (get-in lookup [:paris :madrid])))
    (is (= 314 (get-in lookup [:madrid :paris])) "Can we get back to Paris?")
    (is (not (get-in lookup [:paris :bratislava])))))


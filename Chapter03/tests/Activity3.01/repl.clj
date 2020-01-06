(require '[clojure.test :as test :refer [is are deftest run-tests]])

(load-file "../../Activity3.01/repl.clj")


(def paris {:lat 48.856483 :lon 2.352413})
(def bordeaux {:lat 44.834999  :lon -0.575490})
(def london {:lat 51.507351, :lon -0.127758})
(def manchester {:lat 53.480759, :lon -2.242631})


(deftest activity-301-test
  (is (= {:cost 0, :distance 491.61380776549225, :duration 122.90345194137306} (itinerary {:from paris :to bordeaux :transport :walking})))
  (is (= {:cost 44.7368565066598, :distance 491.61380776549225, :duration 7.023054396649889} (itinerary {:from paris :to bordeaux :transport :driving :vehicle :tayato})))
  (is (= {:cost 0, :distance 318.4448148814284, :duration 79.6112037203571} (itinerary {:from london :to manchester :transport :walking})))
  (is (= {:cost 4.604730845743489, :distance 230.2365422871744, :duration 3.2890934612453484} (itinerary {:from manchester :to london :transport :driving :vehicle :sleta}))))

(run-tests)

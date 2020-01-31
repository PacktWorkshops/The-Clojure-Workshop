(ns packt-clj.chapter-5-tests.exercise-5-04-test
  (:require  [clojure.test :as t :refer [deftest is testing]]))

(def matches  
  [{:winner-name "Kvitova P.",
    :loser-name "Ostapenko J.",
    :tournament "US Open",
    :location "New York",
    :date "2016-08-29"}
   {:winner-name "Kvitova P.",
    :loser-name "Buyukakcay C.",
    :tournament "US Open",
    :location "New York",
    :date "2016-08-31"}
   {:winner-name "Kvitova P.",
    :loser-name "Svitolina E.",
    :tournament "US Open",
    :location "New York",
    :date "2016-09-02"}
   {:winner-name "Kerber A.",
    :loser-name "Kvitova P.",
    :tournament "US Open",
    :location "New York",
    :date "2016-09-05"}
   {:winner-name "Kvitova P.",
    :loser-name "Brengle M.",
    :tournament "Toray Pan Pacific Open",
    :location "Tokyo",
    :date "2016-09-20"}
   {:winner-name "Puig M.",
    :loser-name "Kvitova P.",
    :tournament "Toray Pan Pacific Open",
    :location "Tokyo",
    :date "2016-09-21"}])


(deftest matches-by-date
  (let [lookup (zipmap (map :date matches) matches)]
    (is (= {:winner-name "Kvitova P.",
            :loser-name "Buyukakcay C.",
            :tournament "US Open",
            :location "New York",
            :date "2016-08-31"}
           (get lookup "2016-08-31")))
    (is (= {:winner-name "Kvitova P.",
            :loser-name "Brengle M.",
            :tournament "Toray Pan Pacific Open",
            :location "Tokyo",
            :date "2016-09-20"}
           (get lookup "2016-09-20")))))


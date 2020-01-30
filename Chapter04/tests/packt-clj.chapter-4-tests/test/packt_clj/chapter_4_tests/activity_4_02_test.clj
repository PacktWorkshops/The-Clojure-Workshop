(ns packt-clj.chapter-4-tests.activity-4-02-test
  (:require
   [clojure.test :as t :refer [deftest is testing]]
   [clojure.java.io :as io]
   [packt-clj.chapter-4-tests.activity-4-02 :as exo]))


;;; For convenience, we reference the csv file as a resource.
(def data (io/file (io/resource "match_scores_1991-2016_unindexed_csv.csv")))


(deftest rivalry-count
  (let [rivalry  (exo/rivalry-data data "Roger Federer" "Rafael Nadal")]
    (is (map? rivalry))
    (is (= 34 (:total-matches rivalry)))
    (is (= 11 (:total-victories-player-1 rivalry)))
    (is (= 23 (:total-victories-player-2 rivalry)))))


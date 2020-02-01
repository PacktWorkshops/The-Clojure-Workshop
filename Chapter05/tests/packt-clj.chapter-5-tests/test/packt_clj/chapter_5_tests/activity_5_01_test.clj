(ns packt-clj.chapter-5-tests.activity-5-01-test
  (:require [clojure.test :as t :refer [deftest is testing]]
            [packt-clj.chapter-5-tests.activity-5-01 :as tennis]
            [clojure.java.io :as io]))

;;; For convenience, we reference the csv file as a resource.
(def data (io/file (io/resource "match_scores_1991-2016_unindexed_csv.csv")))

(deftest elo-world-simple
  (let [ratings (tennis/elo-world-simple data 32)]
    (is (= 1119 (int (get-in ratings [:players "roger-federer"]))))
    (is (= 969 (int (get-in ratings [:players "rafael-nadal"]))))
    (is (= 744 (int (get-in ratings [:players "andre-agassi"]))))

    (is (= 95356 (:predictable-match-count ratings)))
    (is (= 61991 (:correct-predictions ratings)))))




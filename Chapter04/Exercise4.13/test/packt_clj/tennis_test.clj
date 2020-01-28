(ns packt-clj.tennis-test
  (:require
   [clojure.test :as t :refer [deftest is testing]]
   [packt-clj.tennis :as tennis]))

(def tennis-data-csv "../tennis-data/match_scores_1991-2016_unindexed_csv.csv")

(deftest verify-federer-wins
  (let [fed-wins (tennis/federer-wins tennis-data-csv)]
    (is (> (count fed-wins) 10))
    (is (every? map? fed-wins))
    (is (every? #(= "Roger Federer" (:winner_name %)) fed-wins))))

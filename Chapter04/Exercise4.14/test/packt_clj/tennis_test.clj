(ns packt-clj.tennis-test
  (:require [packt-clj.tennis :as tennis]
            [clojure.test :refer [testing is deftest]]))

(def tennis-data-csv "../tennis-data/match_scores_1991-2016_unindexed_csv.csv")

(deftest federer-win-match-query
  (let [fed-wins (tennis/match-query tennis-data-csv
                                     #(= "Roger Federer" (:winner_name %)))]
    (is (> (count fed-wins) 10))
    (is (every? map? fed-wins))
    (is (every? #(= "Roger Federer" (:winner_name %)) fed-wins))))


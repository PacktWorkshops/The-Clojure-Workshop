(ns packt-clj.tennis-test
  (:require [packt-clj.tennis :as tennis]
            [clojure.test :refer [testing is deftest]]))

(def tennis-data-csv "../tennis-data/match_scores_1991-2016_unindexed_csv.csv")


(deftest verify-rivalry
  (let [fed-nadal-matches (tennis/match-query tennis-data-csv
                                              #(and (= (hash-set (:winner_name %) (:loser_name %)) 
                                                       #{"Roger Federer" "Rafael Nadal"})
                                                    (= 1 (- (:winner_sets_won %) (:loser_sets_won %)))))]

    (is (> (count fed-nadal-matches) 10))
    (is (integer? (:winner_sets_won (first fed-nadal-matches))))))

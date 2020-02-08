(ns packt-clj.tennis-macro
  (:require [clojure.java.io :as io]
            [clojure.data.csv :as csv]
            [semantic-csv.core :as sc]))

(comment
  ;; The target code
  (with-open [reader (io/reader csv)]
    (->> (csv/read-csv reader)
         sc/mappify
         (sc/cast-with {:winner_games_won sc/->int
                        :loser_games_won sc/->int})
         (map #(assoc % :games_diff (- (:winner_games_won %) (:loser_games_won %))))
         (filter #(> (:games_diff %) threshold))
         (map #(select-keys % [:winner_name :loser_name :games_diff]))
         doall)))


(comment
  ;; Won't work until we've defined with-tennis-csv
  (defn blowouts [csv threshold]
    (with-tennis-csv csv
      {:winner_games_won sc/->int :loser_games_won sc/->int}
      [:winner_name :loser_name :games_diff]
      (map #(assoc % :games_diff (- (:winner_games_won %) (:loser_games_won %))))
      (filter #(> (:games_diff %) threshold)))))



 
(defn maybe-select-keys [m maybe-keys]
  (if (seq maybe-keys)
    (select-keys m maybe-keys)
    m))

(defmacro with-tennis-csv [csv casts fields & forms]
  `(with-open [reader# (io/reader ~csv)]
     (->> (csv/read-csv reader#)
          sc/mappify
          (sc/cast-with ~casts)

          ~@forms

          (map #(maybe-select-keys % ~fields))
          doall)))

(comment
  ;; Testing: either copy the CSV files mentioned here from
  ;; /Chapter04/tennis-data or provide absolute paths
  (blowouts "match_scores_1991-2016_unindexed_csv.csv" 16)
  (with-tennis-csv "match_scores_1991-2016_unindexed_csv.csv" 
    {} 
    [:winner_name] 
    (filter #(= "Roger Federer" (:loser_name %)))))




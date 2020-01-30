(ns packt-clj.chapter-4-tests.exercise-4-15
  (:require
   [clojure.data.csv :as csv]
   [clojure.java.io :as io]
   [semantic-csv.core :as sc]))


(defn match-query [csv pred]
    (with-open [r (io/reader csv)]
    (->> (csv/read-csv r)
         sc/mappify
         (sc/cast-with {:winner_sets_won sc/->int
                        :loser_sets_won sc/->int
                        :winner_games_won sc/->int
                        :loser_games_won sc/->int})
         (filter pred)
         (map #(select-keys % [:winner_name
                               :loser_name
                               :winner_sets_won
                               :loser_sets_won
                               :winner_games_won
                               :loser_games_won
                               :tourney_year_id
                               :tourney_slug]))
         doall)))


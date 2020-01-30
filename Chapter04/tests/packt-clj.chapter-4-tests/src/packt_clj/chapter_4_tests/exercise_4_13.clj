(ns packt-clj.chapter-4-tests.exercise-4-13
  (:require
   [clojure.data.csv :as csv]
   [clojure.java.io :as io]
   [semantic-csv.core :as sc]))

(defn federer-wins [csv]
    (with-open [r (io/reader csv)]
    (->> (csv/read-csv r)
         sc/mappify
         (filter #(= "Roger Federer" (:winner_name %)))
         (map #(select-keys % [:winner_name
                               :loser_name
                               :winner_sets_won
                               :loser_sets_won
                               :winner_games_won
                               :loser_games_won
                               :tourney_year_id
                               :tourney_slug]))
         doall)))

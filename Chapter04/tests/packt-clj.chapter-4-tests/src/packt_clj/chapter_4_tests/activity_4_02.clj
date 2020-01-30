(ns packt-clj.chapter-4-tests.activity-4-02
  (:require
   [clojure.data.csv :as csv]
   [clojure.java.io :as io]
   [semantic-csv.core :as sc]))



(defn rivalry-data [csv player-1 player-2]
  (with-open [r (io/reader csv)]
    (let [rivalry-seq (->> (csv/read-csv r)
                           sc/mappify
                           (sc/cast-with {:winner_sets_won sc/->int
                                          :loser_sets_won sc/->int
                                          :winner_games_won sc/->int
                                          :loser_games_won sc/->int})
                           (filter #(= (hash-set (:winner_name %) (:loser_name %))
                                       #{player-1 player-2}))

                           (map #(select-keys % [:winner_name
                                                 :loser_name
                                                 :winner_sets_won
                                                 :loser_sets_won
                                                 :winner_games_won
                                                 :loser_games_won
                                                 :tourney_year_id
                                                 :tourney_slug])))
          player-1-victories (filter #(= (:winner_name %) player-1) rivalry-seq)
          player-2-victories (filter #(= (:winner_name %) player-2) rivalry-seq) ]
      {:first-victory-player-1 (first player-1-victories)
       :first-victory-player-2 (first player-2-victories)
       :total-matches (count rivalry-seq)
       :total-victories-player-1 (count player-1-victories)
       :total-victories-player-2 (count player-2-victories)
       :most-competitive-matches (->> rivalry-seq
                                      (filter #(= 1 (- (:winner_sets_won %) (:loser_sets_won %)))))})))

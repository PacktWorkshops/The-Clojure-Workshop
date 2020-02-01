(ns packt-clj.chapter-5-tests.activity-5-01
  (:require [clojure.math.numeric-tower :as math]
            [clojure.java.io :as io]
            [clojure.data.csv :as csv]
            [clojure.set :as set]
            [semantic-csv.core :as sc]))



(defn match-probability [player-1-rating player-2-rating]
  (/ 1
     (+ 1 (math/expt 10 (/ (- player-2-rating player-1-rating) 400)))))

(defn recalculate-rating [k previous-rating expected-outcome real-outcome]
  (+ previous-rating (* k (- real-outcome expected-outcome))))

(defn elo-world-simple
  ([csv k]
   (with-open [r (io/reader csv)]
     (->> (csv/read-csv r)
          sc/mappify
          (sc/cast-with {:winner_sets_won sc/->int
                         :loser_sets_won sc/->int
                         :winner_games_won sc/->int
                         :loser_games_won sc/->int})
          (reduce (fn [{:keys [players] :as acc} {:keys [:winner_name :winner_slug
                                                         :loser_name :loser_slug] :as match}]
                    (let [winner-rating (get players winner_slug 400)
                          loser-rating (get players loser_slug 400)
                          winner-probability (match-probability winner-rating loser-rating)
                          loser-probability (- 1 winner-probability)
                          predictable-match? (not= winner-rating loser-rating)
                          prediction-correct? (> winner-rating loser-rating)
                          correct-predictions (if (and predictable-match? prediction-correct?)
                                                (inc (:correct-predictions acc))
                                                (:correct-predictions acc))
                          predictable-matches (if predictable-match?
                                                (inc (:predictable-match-count acc))
                                                (:predictable-match-count acc))]
                      
                      (-> acc
                          (assoc :predictable-match-count predictable-matches)
                          (assoc :correct-predictions correct-predictions)
                          (assoc-in [:players winner_slug] (recalculate-rating k winner-rating winner-probability 1))
                          (assoc-in [:players loser_slug] (recalculate-rating k loser-rating loser-probability 0))
                          (update :match-count inc))))
                  {:players {}
                   :match-count 0
                   :predictable-match-count 0
                   :correct-predictions 0})))))


(defn k-adjustment-by-match [k {:keys [winner_sets_won loser_sets_won
                                   winner_games_won loser_games_won]}]
  (cond (> (- winner_sets_won loser_sets_won) 1)
        (* k 1.2)

        (< (- winner_games_won loser_games_won) 3)
        (* k 0.6)

        ::otherwise
        k))


(defn k-adjustment-by-ratings [k winner-rating loser-rating]
  (cond (= winner-rating loser-rating)
        (* k 0.5)

        (and (> loser-rating winner-rating)
             (> (- loser-rating winner-rating) 100))
        (* k 1.1)

        (> loser-rating winner-rating)
        (* k 1.2)

        ::otherwise
        k))


(defn elo-world-k-adjustment
  ([csv k k-fn]
   (with-open [r (io/reader csv)]
     (->> (csv/read-csv r)
          sc/mappify
          (sc/cast-with {:winner_sets_won sc/->int
                         :loser_sets_won sc/->int
                         :winner_games_won sc/->int
                         :loser_games_won sc/->int})
          (reduce (fn [{:keys [players] :as acc} {:keys [:winner_name :winner_slug
                                                         :loser_name :loser_slug] :as match}]
                    (let [winner-rating (get players winner_slug 400)
                          loser-rating (get players loser_slug 400)
                          winner-probability (match-probability winner-rating loser-rating)
                          loser-probability (- 1 winner-probability)
                          predictable-match? (not= winner-rating loser-rating)
                          prediction-correct? (> winner-rating loser-rating)
                          correct-predictions (if (and predictable-match? prediction-correct?)
                                                (inc (:correct-predictions acc))
                                                (:correct-predictions acc))
                          predictable-matches (if predictable-match?
                                                (inc (:predictable-match-count acc))
                                                (:predictable-match-count acc))
                          adjusted-k (k-fn k winner-rating loser-rating)]
                      
                      (-> acc
                          (assoc :predictable-match-count predictable-matches)
                          (assoc :correct-predictions correct-predictions)
                          (assoc-in [:players winner_slug] (recalculate-rating adjusted-k winner-rating winner-probability 1))
                          (assoc-in [:players loser_slug] (recalculate-rating adjusted-k loser-rating loser-probability 0))
                          (update :match-count inc))))
                  {:players {}
                   :match-count 0
                   :predictable-match-count 0
                   :correct-predictions 0})))))


(defn elo-world-k-adjustment-acc-param
  ([acc csv k k-fn]
   (with-open [r (io/reader csv)]
     (->> (csv/read-csv r)
          sc/mappify
          (sc/cast-with {:winner_sets_won sc/->int
                         :loser_sets_won sc/->int
                         :winner_games_won sc/->int
                         :loser_games_won sc/->int})
          (reduce (fn [{:keys [players] :as acc} {:keys [:winner_name :winner_slug
                                                         :loser_name :loser_slug] :as match}]
                    (let [winner-rating (get players winner_slug 400)
                          loser-rating (get players loser_slug 400)
                          winner-probability (match-probability winner-rating loser-rating)
                          loser-probability (- 1 winner-probability)
                          predictable-match? (not= winner-rating loser-rating)
                          prediction-correct? (> winner-rating loser-rating)
                          correct-predictions (if (and predictable-match? prediction-correct?)
                                                (inc (:correct-predictions acc))
                                                (:correct-predictions acc))
                          predictable-matches (if predictable-match?
                                                (inc (:predictable-match-count acc))
                                                (:predictable-match-count acc))
                          adjusted-k (k-fn k winner-rating loser-rating)]
                      
                      (-> acc
                          (assoc :predictable-match-count predictable-matches)
                          (assoc :correct-predictions correct-predictions)
                          (assoc-in [:players winner_slug] (recalculate-rating adjusted-k winner-rating winner-probability 1))
                          (assoc-in [:players loser_slug] (recalculate-rating adjusted-k loser-rating loser-probability 0))
                          (update :match-count inc))))
                  acc)))))


(defn multi-elo [csv-list k k-fn]
  (reduce (fn [acc csv]
            (elo-world-k-adjustment-acc-param acc csv k k-fn))
          {:players {}
           :match-count 0
           :predictable-match-count 0
           :correct-predictions 0}
          csv-list))


;;; to call multi-elo
(comment
  ;; packt-clj.tennis>
  (def multi-results (multi-elo 
                       ["match_scores_1877-1967_unindexed_csv.csv"
                        "match_scores_1968-1990_unindexed_csv.csv"
                        "match_scores_1991-2016_unindexed_csv.csv"]) 
    40 
    k-adjustment-by-ratings))



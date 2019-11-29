(ns packt-clj.tennis.elo
  (:require
    [clojure.java.jdbc :as jdbc]
    [packt-clj.tennis.query :as query]))

(def ^:private k-factor 32)

(defn- match-probability [player-1-rating player-2-rating]
  (/ 1
     (+ 1 (Math/pow 10 (/ (- player-2-rating player-1-rating) 1000)))))

(defn- recalculate-rating [previous-rating expected-outcome real-outcome]
  (+ previous-rating (* k-factor (- real-outcome expected-outcome))))

(defn- expected-outcomes
  [winner-rating loser-rating]
  (let [winner-expected-outcome (match-probability winner-rating loser-rating)]
    [winner-expected-outcome (- 1 winner-expected-outcome)]))

(defn- calculate-new-ratings [current-player-ratings {:keys [winner_id loser_id]}]
  (let [winner-rating (get current-player-ratings winner_id 1000)
        loser-rating  (get current-player-ratings loser_id 1000)
        [winner-expected-outcome loser-expected-outcome] (expected-outcomes winner-rating loser-rating)]
    [{:player_id winner_id
      :rating    (recalculate-rating winner-rating winner-expected-outcome 1)}
     {:player_id loser_id
      :rating    (recalculate-rating loser-rating loser-expected-outcome 0)}]))

(defn calculate-all
  [db]
  (->> (query/all-tennis-matches db)
       (reduce
         (fn [{:keys [current-player-ratings] :as acc} match]
           (let [[{winner-id :player_id :as new-winner-rating} {loser-id :player_id :as new-loser-rating}] (calculate-new-ratings current-player-ratings match)]
             (-> acc
                 (update :elo-ratings into [new-winner-rating
                                            new-loser-rating])
                 (assoc-in [:current-player-ratings winner-id] (:rating new-winner-rating))
                 (assoc-in [:current-player-ratings loser-id] (:rating new-loser-rating)))))
         {:elo-ratings            []
          :current-player-ratings {}})
       :elo-ratings))

(defn persist-all
  [db]
  (let [elo-ratings (calculate-all db)]
    (jdbc/insert-multi! db :elo elo-ratings)))

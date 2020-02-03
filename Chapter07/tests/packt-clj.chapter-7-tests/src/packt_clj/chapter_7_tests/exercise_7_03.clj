(ns packt-clj.chapter-7-tests.exercise-7-03
  (:require
   [clojure.data.csv :as csv]
   [clojure.java.io :as io]
   [clojure.math.numeric-tower :as math]
   [semantic-csv.core :as sc]))


;;; The first part of this file comes from the tennis_history.clj
;;; from Exercise 5.03.
(defn match-probability [player-1-rating player-2-rating]
  (/ 1
     (+ 1 (math/expt 10 (/ (- player-2-rating player-1-rating) 400)))))

(defn recalculate-rating [k previous-rating expected-outcome real-outcome]
  (+ previous-rating (* k (- real-outcome expected-outcome))))

(defn elo-db [csv k]
  (with-open [r (io/reader csv)]
    (->> (csv/read-csv r)
         sc/mappify
         (sc/cast-with {:winner_sets_won sc/->int
                        :loser_sets_won sc/->int
                        :winner_games_won sc/->int
                        :loser_games_won sc/->int})
         (reduce (fn [{:keys [players] :as acc}
                      {:keys [_winner_name winner_slug
                              _loser_name loser_slug] :as match}]
                   (let [winner-rating (get players winner_slug 400)
                         loser-rating (get players loser_slug 400)
                         winner-probability (match-probability
                                             winner-rating
                                             loser-rating)
                         loser-probability (- 1 winner-probability)]
                     (-> acc
                         (assoc-in [:players winner_slug]
                                   (recalculate-rating k
                                                       winner-rating
                                                       winner-probability 1))
                         (assoc-in [:players loser_slug]
                                   (recalculate-rating k
                                                       loser-rating
                                                       loser-probability 0))
                         (update :matches
                                 (fn [ms]
                                   (conj ms
                                         (assoc match
                                                :winner_rating winner-rating
                                                :loser_rating loser-rating)))))))
                 {:players {}
                  :matches []})
         :matches
         reverse
         )))

(defn player-in-match? [{:keys [winner_slug loser_slug]} player-slug]
  ((hash-set winner_slug loser_slug) player-slug))



(defn match-tree-by-player [matches player-slug]
  (lazy-seq
   (cond (empty? matches)
         '()
         (player-in-match? (first matches) player-slug)
         (cons
          (first matches)
          (cons
           [(match-tree-by-player (rest matches) (:winner_slug (first matches)))
            (match-tree-by-player (rest matches) (:loser_slug (first matches)))]
           '()))
         :else
         (match-tree-by-player (rest matches) player-slug))))



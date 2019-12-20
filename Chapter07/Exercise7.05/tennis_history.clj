(ns packt-clojure.lazy-tennis
  (:require [clojure.math.numeric-tower :as math]
            [clojure.java.io :as io]
            [clojure.data.csv :as csv]
            [semantic-csv.core :as sc]))

;;; The first part of this file is identical to the tennis_history.clj
;;; from Exercise 5.04.
(defn match-probability [player-1-rating player-2-rating]
  (/ 1
     (+ 1 (math/expt 10 (/ (- player-2-rating player-1-rating) 400)))))

(defn recalculate-rating [k previous-rating expected-outcome real-outcome]
  (+ previous-rating (* k (- real-outcome expected-outcome))))


(defn elo-db
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
                          loser-probability (- 1 winner-probability)]
                      
                      (-> acc
                          (assoc-in [:players winner_slug] (recalculate-rating k winner-rating winner-probability 1))
                          (assoc-in [:players loser_slug] (recalculate-rating k loser-rating loser-probability 0))
                          (update :matches (fn [ms]
                                             (conj ms (assoc match
                                                             :winner_rating winner-rating
                                                             :loser_rating loser-rating)))))))
                  {:players {}
                   :matches []})
          :matches
          reverse))))


(defn player-in-match? [{:keys [winner_slug loser_slug]} player-slug]
  ((hash-set winner_slug loser_slug) player-slug))


(defn match-tree-by-player [m player-slug]
  (lazy-seq
    (cond (empty? m)
          '()
          (player-in-match?  (first m) player-slug)
          (cons (first m)
                (cons
                  [(match-tree-by-player (rest m) (:winner_slug (first m)))
                   (match-tree-by-player (rest m) (:loser_slug (first m)))]
                  '()))
          ::otherwise
          (match-tree-by-player (rest m) player-slug))))

;;; The new functions start here
(defn take-matches [limit tree f]
  (cond (zero? limit)
        '()
        (= 1 limit)
        (f (first tree))

        :otherwise-continue
        (cons
          (f (first tree))
          (cons
            [(take-matches (dec limit) (first (second tree)) f)
             (take-matches (dec limit) (second (second tree)) f)]
            '()))))

(defn matches-with-ratings [limit tree]
  (take-matches limit
                tree
                (fn [match]
                  (-> match
                      (update :winner_rating int)
                      (update :loser_rating int)
                      (select-keys [:winner_name :loser_name :winner_rating :loser_rating])
                      (assoc :winner_probability_percentage (->> (match-probability (:winner_rating match)
                                                                        (:loser_rating match))
                                                      (* 100)
                                                      int))))))



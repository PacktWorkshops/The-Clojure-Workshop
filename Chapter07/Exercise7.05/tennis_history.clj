(ns packt-clj.tennis
  (:require
   [clojure.data.csv :as csv]
   [clojure.java.io :as io]
   [clojure.math.numeric-tower :as math]
   [semantic-csv.core :as sc]))

;;; The first part of this file comes from the tennis_history.clj
;;; from Exercise 5.04.
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
         reverse)))

(def matches
  (elo-db "match_scores_1991-2016_unindexed_csv.csv" 35))

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

(def federer
  (match-tree-by-player matches "roger-federer"))

;;; The new functions start here

(defn take-matches
  ([limit tree]
   (take-matches limit tree identity))
  ([limit tree f]
   (cond (zero? limit)
         '()
         (= 1 limit)
         (f (first tree))
         :else
         (cons
          (f (first tree))
          (cons
           [(take-matches (dec limit) (first (second tree)) f)
            (take-matches (dec limit) (second (second tree)) f)]
           '())))))

(defn matches-with-ratings [limit tree]
  (take-matches limit
                tree
                (fn [match]
                  (-> match
                      (update :winner_rating int)
                      (update :loser_rating int)
                      (select-keys [:winner_name
                                    :loser_name
                                    :winner_rating
                                    :loser_rating])
                      (assoc :winner_probability_percentage
                             (->> (match-probability
                                   (:winner_rating match)
                                   (:loser_rating match))
                                  (* 100)
                                  (int)))))))

(matches-with-ratings 3 federer)
;; => ({:winner_name "Roger Federer",
;;      :loser_name "Guido Pella",
;;      :winner_rating 1129,
;;      :loser_rating 625,
;;      :winner_probability_percentage 94}
;;     [({:winner_name "Roger Federer",
;;        :loser_name "Marcus Willis",
;;        :winner_rating 1128,
;;        :loser_rating 384,
;;        :winner_probability_percentage 98}
;;       [{:winner_name "Roger Federer",
;;         :loser_name "Daniel Evans",
;;         :winner_rating 1127,
;;         :loser_rating 603,
;;         :winner_probability_percentage 95}
;;        {:winner_name "Pierre-Hugues Herbert",
;;         :loser_name "Marcus Willis",
;;         :winner_rating 587,
;;         :loser_rating 392,
;;         :winner_probability_percentage 75}])
;;      ({:winner_name "Benjamin Becker",
;;        :loser_name "Guido Pella",
;;        :winner_rating 638,
;;        :loser_rating 643,
;;        :winner_probability_percentage 49}
;;       [{:winner_name "Dudi Sela",
;;         :loser_name "Benjamin Becker",
;;         :winner_rating 560,
;;         :loser_rating 661,
;;         :winner_probability_percentage 35}
;;        {:winner_name "Guido Pella",
;;         :loser_name "Diego Schwartzman",
;;         :winner_rating 623,
;;         :loser_rating 665,
;;         :winner_probability_percentage 43}])])

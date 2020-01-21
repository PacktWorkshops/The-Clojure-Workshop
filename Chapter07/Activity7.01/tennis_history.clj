(ns packt-clj.tennis
  (:require
   [clojure.data.csv :as csv]
   [clojure.java.io :as io]
   [clojure.math.numeric-tower :as math]
   [semantic-csv.core :as sc]))

;;; The first part of this file is identical to the tennis_history.clj
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

;;; The new functions start here

(defn focus-history [tree player-slug focus-depth opponent-depth f]
  (cond (zero? focus-depth)
        '()
        (= 1 focus-depth)
        (f (first tree))
        :else
        (cons
         (f (first tree))
         (cons
          [(if (player-in-match? (ffirst (second tree)) player-slug)
             (focus-history (first (second tree))
                            player-slug
                            (dec focus-depth)
                            opponent-depth
                            f)
             (take-matches opponent-depth (first (second tree)) f))
           (if (player-in-match? (first (second (second tree))) player-slug)
             (focus-history (second (second tree))
                            player-slug
                            (dec focus-depth)
                            opponent-depth
                            f)
             (take-matches opponent-depth (second (second tree)) f))]
          '()))))

(focus-history federer
               "roger-federer"
               4
               2
               #(select-keys % [:winner_name
                                :loser_name
                                :winner_rating
                                :loser_rating]))
;; => ({:winner_name "Roger Federer",
;;      :loser_name "Guido Pella",
;;      :winner_rating 1129.178155312036,
;;      :loser_rating 625.3431873490674}
;;     [({:winner_name "Roger Federer",
;;        :loser_name "Marcus Willis",
;;        :winner_rating 1128.703390288765,
;;        :loser_rating 384.0402195770708}
;;       [({:winner_name "Roger Federer",
;;          :loser_name "Daniel Evans",
;;          :winner_rating 1127.06735832767,
;;          :loser_rating 603.2729932046952}
;;         [{:winner_name "Roger Federer",
;;           :loser_name "Steve Johnson",
;;           :winner_rating 1122.7516522815502,
;;           :loser_rating 782.0059408768791}
;;          ({:winner_name "Daniel Evans",
;;            :loser_name "Liam Broady",
;;            :winner_rating 595.9600755434737,
;;            :loser_rating 364.68547206909847}
;;           [{:winner_name "Daniel Evans",
;;             :loser_name "Ricardas Berankis",
;;             :winner_rating 579.3218764693715,
;;             :loser_rating 562.1982931522488}
;;            {:winner_name "Inigo Cervantes",
;;             :loser_name "Liam Broady",
;;             :winner_rating 470.1485260098959,
;;             :loser_rating 377.6326375223018}])])
;;        ({:winner_name "Pierre-Hugues Herbert",
;;          :loser_name "Marcus Willis",
;;          :winner_rating 587.634881154115,
;;          :loser_rating 392.63430666689504}
;;         [{:winner_name "Pierre-Hugues Herbert",
;;           :loser_name "Daniil Medvedev",
;;           :winner_rating 578.8847540928984,
;;           :loser_rating 388.03961566213536}
;;          {:winner_name "Daniel Kosakowski",
;;           :loser_name "Marcus Willis",
;;           :winner_rating 475.1397044731464,
;;           :loser_rating 406.73254794551923}])])
;;      ({:winner_name "Benjamin Becker",
;;        :loser_name "Guido Pella",
;;        :winner_rating 638.7921462877312,
;;        :loser_rating 643.0580458561587}
;;       [{:winner_name "Dudi Sela",
;;         :loser_name "Benjamin Becker",
;;         :winner_rating 560.0126295247488,
;;         :loser_rating 661.2518854789847}
;;        {:winner_name "Guido Pella",
;;         :loser_name "Diego Schwartzman",
;;         :winner_rating 623.4399911176613,
;;         :loser_rating 665.6978632936225}])])

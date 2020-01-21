(ns packt-clj.tennis
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

(defn take-matches [limit tree]
  (cond (zero? limit)
        '()
        (= 1 limit)
        (first tree)
        :else
        (cons
         (first tree)
         (cons
          [(take-matches (dec limit) (first (second tree)))
           (take-matches (dec limit) (second (second tree)))]
          '()))))

(take-matches 0 federer)
;; => ()

(select-keys (take-matches 1 federer) [:winner_slug :loser_slug])
;; => {:winner_slug "roger-federer", :loser_slug "guido-pella"}

(take-matches 3 federer)
;; => ({:tourney_slug "wimbledon",
;;      :loser_slug "guido-pella",
;;      :winner_sets_won 3,
;;      :match_score_tiebreaks "76(5) 76(3) 63",
;;      :loser_sets_won 0,
;;      :loser_games_won 15,
;;      :tourney_year_id "2016-540",
;;      :tourney_order "39",
;;      :winner_seed "3",
;;      :loser_seed "",
;;      :winner_slug "roger-federer",
;;      :match_order "3",
;;      :loser_name "Guido Pella",
;;      :winner_player_id "f324",
;;      :match_stats_url_suffix "/en/scores/2016/540/MS080/match-stats",
;;      :tourney_url_suffix "/en/scores/archive/wimbledon/540/2016/results",
;;      :loser_player_id "pc11",
;;      :loser_tiebreaks_won "0",
;;      :round_order "7",
;;      :winner_rating 1129.178155312036,
;;      :tourney_round_name "Round of 128",
;;      :match_id "2016-540-f324-pc11",
;;      :winner_name "Roger Federer",
;;      :winner_games_won 20,
;;      :loser_rating 625.3431873490674,
;;      :winner_tiebreaks_won "2"}
;;     [({:tourney_slug "wimbledon",
;;        :loser_slug "marcus-willis",
;;        :winner_sets_won 3,
;;        :match_score_tiebreaks "60 63 64",
;;        :loser_sets_won 0,
;;        :loser_games_won 7,
;;        :tourney_year_id "2016-540",
;;        :tourney_order "39",
;;        :winner_seed "3",
;;        :loser_seed "Q",
;;        :winner_slug "roger-federer",
;;        :match_order "3",
;;        :loser_name "Marcus Willis",
;;        :winner_player_id "f324",
;;        :match_stats_url_suffix "/en/scores/2016/540/MS040/match-stats",
;;        :tourney_url_suffix "/en/scores/archive/wimbledon/540/2016/results",
;;        :loser_player_id "w521",
;;        :loser_tiebreaks_won "0",
;;        :round_order "6",
;;        :winner_rating 1128.703390288765,
;;        :tourney_round_name "Round of 64",
;;        :match_id "2016-540-f324-w521",
;;        :winner_name "Roger Federer",
;;        :winner_games_won 18,
;;        :loser_rating 384.0402195770708,
;;        :winner_tiebreaks_won "0"}
;;       [{:tourney_slug "wimbledon",
;;         :loser_slug "daniel-evans",
;;         :winner_sets_won 3,
;;         :match_score_tiebreaks "64 62 62",
;;         :loser_sets_won 0,
;;         :loser_games_won 8,
;;         :tourney_year_id "2016-540",
;;         :tourney_order "39",
;;         :winner_seed "3",
;;         :loser_seed "",
;;         :winner_slug "roger-federer",
;;         :match_order "3",
;;         :loser_name "Daniel Evans",
;;         :winner_player_id "f324",
;;         :match_stats_url_suffix "/en/scores/2016/540/MS020/match-stats",
;;         :tourney_url_suffix "/en/scores/archive/wimbledon/540/2016/results",
;;         :loser_player_id "e687",
;;         :loser_tiebreaks_won "0",
;;         :round_order "5",
;;         :winner_rating 1127.06735832767,
;;         :tourney_round_name "Round of 32",
;;         :match_id "2016-540-f324-e687",
;;         :winner_name "Roger Federer",
;;         :winner_games_won 18,
;;         :loser_rating 603.2729932046952,
;;         :winner_tiebreaks_won "0"}
;;        {:tourney_slug "marseille",
;;         :loser_slug "marcus-willis",
;;         :winner_sets_won 2,
;;         :match_score_tiebreaks "76(7) 76(2)",
;;         :loser_sets_won 0,
;;         :loser_games_won 12,
;;         :tourney_year_id "2015-496",
;;         :tourney_order "14",
;;         :winner_seed "5",
;;         :loser_seed "",
;;         :winner_slug "pierre-hugues-herbert",
;;         :match_order "5",
;;         :loser_name "Marcus Willis",
;;         :winner_player_id "h996",
;;         :match_stats_url_suffix "/en/scores/2015/496/QS019/match-stats",
;;         :tourney_url_suffix "/en/scores/archive/marseille/496/2015/results",
;;         :loser_player_id "w521",
;;         :loser_tiebreaks_won "0",
;;         :round_order "8",
;;         :winner_rating 587.634881154115,
;;         :tourney_round_name "1st Round Qualifying",
;;         :match_id "2015-496-h996-w521",
;;         :winner_name "Pierre-Hugues Herbert",
;;         :winner_games_won 14,
;;         :loser_rating 392.63430666689504,
;;         :winner_tiebreaks_won "2"}])
;;      ({:tourney_slug "eastbourne",
;;        :loser_slug "guido-pella",
;;        :winner_sets_won 2,
;;        :match_score_tiebreaks "36 61 63",
;;        :loser_sets_won 1,
;;        :loser_games_won 10,
;;        :tourney_year_id "2016-741",
;;        :tourney_order "38",
;;        :winner_seed "",
;;        :loser_seed "13",
;;        :winner_slug "benjamin-becker",
;;        :match_order "13",
;;        :loser_name "Guido Pella",
;;        :winner_player_id "b896",
;;        :match_stats_url_suffix "/en/scores/2016/741/MS021/match-stats",
;;        :tourney_url_suffix "/en/scores/archive/eastbourne/741/2016/results",
;;        :loser_player_id "pc11",
;;        :loser_tiebreaks_won "0",
;;        :round_order "5",
;;        :winner_rating 638.7921462877312,
;;        :tourney_round_name "Round of 32",
;;        :match_id "2016-741-b896-pc11",
;;        :winner_name "Benjamin Becker",
;;        :winner_games_won 15,
;;        :loser_rating 643.0580458561587,
;;        :winner_tiebreaks_won "0"}
;;       [{:tourney_slug "eastbourne",
;;         :loser_slug "benjamin-becker",
;;         :winner_sets_won 2,
;;         :match_score_tiebreaks "63 26 64",
;;         :loser_sets_won 1,
;;         :loser_games_won 13,
;;         :tourney_year_id "2016-741",
;;         :tourney_order "38",
;;         :winner_seed "",
;;         :loser_seed "",
;;         :winner_slug "dudi-sela",
;;         :match_order "8",
;;         :loser_name "Benjamin Becker",
;;         :winner_player_id "sc56",
;;         :match_stats_url_suffix "/en/scores/2016/741/MS010/match-stats",
;;         :tourney_url_suffix "/en/scores/archive/eastbourne/741/2016/results",
;;         :loser_player_id "b896",
;;         :loser_tiebreaks_won "0",
;;         :round_order "4",
;;         :winner_rating 560.0126295247488,
;;         :tourney_round_name "Round of 16",
;;         :match_id "2016-741-sc56-b896",
;;         :winner_name "Dudi Sela",
;;         :winner_games_won 14,
;;         :loser_rating 661.2518854789847,
;;         :winner_tiebreaks_won "0"}
;;        {:tourney_slug "roland-garros",
;;         :loser_slug "diego-schwartzman",
;;         :winner_sets_won 3,
;;         :match_score_tiebreaks "62 36 62 63",
;;         :loser_sets_won 1,
;;         :loser_games_won 13,
;;         :tourney_year_id "2016-520",
;;         :tourney_order "33",
;;         :winner_seed "",
;;         :loser_seed "",
;;         :winner_slug "guido-pella",
;;         :match_order "59",
;;         :loser_name "Diego Schwartzman",
;;         :winner_player_id "pc11",
;;         :match_stats_url_suffix "/en/scores/2016/520/MS105/match-stats",
;;         :tourney_url_suffix "/en/scores/archive/roland-garros/520/2016/results",
;;         :loser_player_id "sm37",
;;         :loser_tiebreaks_won "0",
;;         :round_order "7",
;;         :winner_rating 623.4399911176613,
;;         :tourney_round_name "Round of 128",
;;         :match_id "2016-520-pc11-sm37",
;;         :winner_name "Guido Pella",
;;         :winner_games_won 21,
;;         :loser_rating 665.6978632936225,
;;         :winner_tiebreaks_won "0"}])])

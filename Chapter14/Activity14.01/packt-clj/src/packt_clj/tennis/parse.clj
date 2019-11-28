(ns packt-clj.tennis.parse
  (:require
    [clojure.data.csv :as csv]
    [clojure.java.io :as io]
    [clojure.string :as str]
    [semantic-csv.core :as sc]))

(def ^:private winning-player-accessors
  {:id        :winner_player_id
   :full_name :winner_name})

(def ^:private losing-player-accessors
  {:id        :loser_player_id
   :full_name :loser_name})

(def ^:private match-accessors
  {:id               #(str (:match_id %) "-" (:round_order %))
   :tournament_year  (comp first #(str/split % #"-") :tourney_year_id)
   :tournament       :tourney_slug
   :tournament_order :tourney_order
   :round_order      :round_order
   :match_order      :match_order
   :winner_id        :winner_player_id
   :loser_id         :loser_player_id})

(defn apply-accessors
  [row accessors]
  (reduce-kv
    (fn [acc target-key accessor]
      (assoc acc target-key (accessor row)))
    {}
    accessors))

(defn extract-winning-player
  [row]
  (apply-accessors row winning-player-accessors))

(defn extract-losing-player
  [row]
  (apply-accessors row losing-player-accessors))

(defn extract-match
  [row]
  (apply-accessors row match-accessors))

(defn parse-row
  [row]
  {:winning-player (extract-winning-player row)
   :losing-player  (extract-losing-player row)
   :match          (extract-match row)})

(defn new-player?
  [seen candidate]
  (not (seen (:id candidate))))

(defn historic
  [file-path]
  (->> (io/reader file-path)
       (csv/read-csv)
       sc/mappify
       (reduce (fn
                 [{:keys [player-ids-seen] :as acc} row]
                 (let [{:keys [winning-player losing-player match]} (parse-row row)
                       new-players (filter #(new-player? player-ids-seen %) [winning-player losing-player])]
                   (-> acc
                       (update-in [:data :players] into new-players)
                       (update-in [:data :matches] conj match)
                       (update :player-ids-seen into (map :id new-players)))))
               {:player-ids-seen #{}
                :data            {:players []
                                  :matches []}})
       :data))
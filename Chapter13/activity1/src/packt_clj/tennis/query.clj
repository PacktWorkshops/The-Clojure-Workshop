(ns packt-clj.tennis.query
  (:require
    [clojure.java.jdbc :as jdbc]))

(defn all-tennis-matches
  [db]
  (jdbc/query db ["select *
                   from tennis_match
                   order by tournament_year, tournament_order, round_order desc, match_order"]))

(defn select-max-elo
  [db]
  (jdbc/query db ["select p.full_name, e.rating
                   from player p, elo e
                   where p.id = e.player_id"]
              {:result-set-fn (fn [rs]
                                (reduce (fn [{:keys [max-rating] :as acc} {:keys [full_name rating]}]
                                          (cond-> acc
                                                  (< max-rating rating) (assoc :max-rating rating
                                                                               :player-name full_name)))
                                        {:max-rating  Integer/MIN_VALUE
                                         :player-name nil}
                                        rs))}))

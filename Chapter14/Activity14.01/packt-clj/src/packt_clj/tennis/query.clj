(ns packt-clj.tennis.query
  (:require
    [clojure.java.jdbc :as jdbc]))

(defn all-players
  [db]
  (jdbc/query db ["select * from player"]))

(defn player
  [db id]
  (jdbc/query db [(str "select * from player where id = '" id "'")]))

(defn player-elo
  [db id]
  (jdbc/query db [(str "select e.rating, e.id
                        from elo e, player p
                        where e.player_id = p.id and
                        p.id = '" id "' and
                        e.id in (select max(e2.id)
                                 from elo e2
                                 where e2.player_id = '" id "')")]))

(defn all-tennis-matches
  [db]
  (jdbc/query db ["select *
                   from tennis_match
                   order by tournament_year, tournament_order, round_order desc, match_order"]))

(defn tennis-matches-by-player
  [db id]
  (jdbc/query db [(str "select * from tennis_match
                   where winner_id = '" id "' or loser_id = '" id "'")]))

(defn tennis-match
  [db id]
  (jdbc/query db [(str "select * from tennis_match where id = '" id "'")]))


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

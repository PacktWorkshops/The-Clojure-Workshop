(ns packt-clj.tennis.database
  (:require
    [clojure.java.jdbc :as jdbc]
    [hikari-cp.core :as hikari]))

(def db {:datasource (hikari/make-datasource {:jdbc-url "jdbc:derby:tennis;create=true"})})

(def ^:private create-player-ddl "CREATE TABLE player (
  id varchar(4) CONSTRAINT PLAYER_ID_PK PRIMARY KEY,
  full_name varchar(128))")

(def ^:private create-tennis-match-ddl "CREATE TABLE tennis_match (
  id varchar(32) CONSTRAINT MATCH_ID_PK PRIMARY KEY,
  tournament_year int,
  tournament varchar(32),
  tournament_order int,
  round_order int,
  match_order int,
  winner_id varchar(4) REFERENCES player(id) ON DELETE CASCADE,
  loser_id varchar(4) REFERENCES player(id) ON DELETE CASCADE)")

(def ^:private create-elo-ddl "CREATE TABLE elo (
  id int GENERATED ALWAYS AS IDENTITY CONSTRAINT ELO_ID_PK PRIMARY KEY,
  player_id varchar(4) REFERENCES player(id) ON DELETE CASCADE,
  rating DECIMAL(6,2))")

(defn load []
  (jdbc/db-do-commands db [create-player-ddl create-tennis-match-ddl create-elo-ddl]))

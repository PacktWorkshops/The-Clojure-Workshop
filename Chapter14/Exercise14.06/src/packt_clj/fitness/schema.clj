(ns packt-clj.fitness.schema
  (:require
    [clojure.java.jdbc :as jdbc]
    [hikari-cp.core :as hikari]))

(def ^:private jdbc-url "jdbc:derby:derby-local;create=true")
(def db {:datasource (hikari/make-datasource {:jdbc-url jdbc-url})})

(def ^:private create-app-user-ddl "CREATE TABLE app_user (
  id int GENERATED ALWAYS AS IDENTITY CONSTRAINT USER_ID_PK PRIMARY KEY,
  first_name varchar(32),
  surname varchar(32),
  height smallint,
  weight smallint)")

(def ^:private create-activity-ddl "CREATE TABLE activity (
  id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  activity_type VARCHAR(32),
  distance DECIMAL(7,2),
  duration INT,
  activity_date DATE,
  user_id INT REFERENCES app_user ON DELETE CASCADE)")

(defn load
  []
  (jdbc/db-do-commands db [create-app-user-ddl create-activity-ddl]))

(ns packt-clj.exercises.database-schema
  (:require
    [clojure.java.jdbc :as jdbc]))

(def create-app-user-ddl
  "CREATE TABLE app_user (
   id INT GENERATED ALWAYS AS IDENTITY CONSTRAINT USER_ID_PK PRIMARY KEY,
   first_name VARCHAR(32),
   surname VARCHAR(32),
   height SMALLINT,
   weight SMALLINT)")

(def create-activity-ddl
  "CREATE TABLE activity (
   id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
   activity_type VARCHAR(32),
   distance DECIMAL(5,2),
   duration INT,
   user_id INT REFERENCES app_user ON DELETE CASCADE)")

(def create-app-user-ddl-2
  (jdbc/create-table-ddl :app_user
                         [[:id :int "GENERATED ALWAYS AS IDENTITY CONSTRAINT USER_ID_PK PRIMARY KEY"]
                          [:first_name "varchar(32)"]
                          [:surname "varchar(32)"]
                          [:height :smallint]
                          [:weight :smallint]]
                         {:entities clojure.string/lower-case}))


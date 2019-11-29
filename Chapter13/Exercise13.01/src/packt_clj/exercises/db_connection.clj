(ns packt-clj.exercises.db-connection
  (:require
    [clojure.java.jdbc :as jdbc]))

(def db {:dbtype "derby"
         :dbname "derby-local"
         :create true})
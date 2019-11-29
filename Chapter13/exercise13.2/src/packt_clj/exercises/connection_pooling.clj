(ns packt-clj.exercises.connection-pooling
  (:require
    [clojure.java.jdbc :as jdbc]
    [hikari-cp.core :as hikari]))

; option 1
(def db {:datasource (hikari/make-datasource {:jdbc-url "jdbc:derby:derby-local;create=true"})})

; option 2
(def db {:datasource
         (hikari/make-datasource {:database-name         "derby-local"
                                  :datasource-class-name "org.apache.derby.jdbc.EmbeddedDataSource"})})

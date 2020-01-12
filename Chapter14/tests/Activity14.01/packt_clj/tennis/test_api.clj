(ns packt-clj.tennis.test-api
  (:require
    [clj-http.client :as client]
    [clojure.test :refer :all]
    [packt-clj.tennis.api :as api]
    [packt-clj.tennis.elo :as elo]
    [packt-clj.tennis.database :as database]
    [packt-clj.tennis.ingest :as ingest]
    [clojure.java.jdbc :as jdbc]
    [clojure.edn :as edn]))

(deftest test-api

  (let [app (api/run)]
    (database/load)
    (ingest/historic database/db "./resources/match_scores_1991-2016_unindexed_csv.csv")
    (elo/persist-all database/db)

    (is (= {:status 200
            :body   {"id"     :77743
                     "rating" :1770.13}}
           (-> (client/get "http://localhost:8080/players/s402/elo")
               (select-keys [:body :status])
               (update :body edn/read-string))))

    (is (= {:status 200
            :body   {"id"     :190718
                     "rating" :2855.24}}
           (-> (client/get "http://localhost:8080/players/d643/elo")
               (select-keys [:body :status])
               (update :body edn/read-string))))

    (client/put "http://localhost:8080/tennis-matches/2019-1-d643-s403-5"
                {:body (pr-str {:tournament_year  2019,
                                :tournament       "umag",
                                :tournament_order 1,
                                :round_order      5,
                                :match_order      1,
                                :winner_id        "d643",
                                :loser_id         "s402"})})

    (is (= {:status 200
            :body   {"id"     :190720
                     "rating" :1767.69}}
           (-> (client/get "http://localhost:8080/players/s402/elo")
               (select-keys [:body :status])
               (update :body edn/read-string))))

    (is (= {:status 200
            :body   {"id"     :190719
                     "rating" :2857.67}}
           (-> (client/get "http://localhost:8080/players/d643/elo")
               (select-keys [:body :status])
               (update :body edn/read-string))))

    (.stop app)
    (jdbc/execute! database/db ["drop table elo"])
    (jdbc/execute! database/db ["drop table tennis_match"])
    (jdbc/execute! database/db ["drop table player"])))

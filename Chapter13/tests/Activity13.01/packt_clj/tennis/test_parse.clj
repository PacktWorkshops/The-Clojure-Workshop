(ns packt-clj.tennis.test-parse
  (:require
    [clojure.test :refer :all]
    [packt-clj.tennis.parse :as parse]
    [clojure.string :as str]))

(deftest test-parse-row
  (is (= {:match          {:id               "123-10"
                           :tournament_year  "2019"
                           :tournament       "Tournament"
                           :tournament_order 1
                           :round_order      10
                           :match_order      1
                           :winner_id        123
                           :loser_id         456}
          :winning-player {:id        123
                           :full_name "Player A"}
          :losing-player  {:id        456
                           :full_name "Player B"}}
         (parse/parse-row {:tourney_slug     "Tournament",
                           :tourney_year_id  "2019-01-01",
                           :tourney_order    1,
                           :match_order      1,
                           :loser_name       "Player B",
                           :winner_player_id 123,
                           :loser_player_id  456,
                           :round_order      10,
                           :match_id         123,
                           :winner_name      "Player A"}))))

(deftest test-new-player
  (is (true? (parse/new-player? #{1 2 3} {:id 4})))
  (is (false? (parse/new-player? #{1 2 3} {:id 3}))))
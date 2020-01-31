(ns packt-clj.chapter-5-tests.exercise-5-03-test
  (:require  [clojure.test :as t :refer [deftest is testing]]
             [packt-clj.chapter-5-tests.exercise-5-03 :as exo]
             [packt-clj.chapter-5-tests.serena-williams-data :as williams-data]))


(deftest streak-string
  (is (= "First match of the year" (exo/streak-string 0 0)))
  (is (= "Won 5" (exo/streak-string 5 0)))
  (is (= "Lost 5" (exo/streak-string 0 5))))

(deftest calculate-win-loss-streaks
  (testing "with only three matches"
    (let [data [{:loser-sets-won 0,
                  :winner-sets-won 2,
                  :winner-name "Williams S.",
                  :loser-name "Van Uytvanck A.",
                  :tournament "Australian Open",
                  :location "Melbourne",
                 :date "2015-01-20"}
                {:loser-sets-won 0,
                 :winner-sets-won 2,
                 :winner-name "Williams S.",
                 :loser-name "Zvonareva V.",
                 :tournament "Australian Open",
                 :location "Melbourne",
                 :date "2015-01-22"}
                {:loser-sets-won 1,
                 :winner-sets-won 2,
                 :winner-name "Williams S.",
                 :loser-name "Svitolina E.",
                 :tournament "Australian Open",
                 :location "Melbourne",
                 :date "2015-01-24"}]]
      (is (= ["First match of the year" "Won 1" "Won 2"]
             (map :current-streak (exo/serena-williams-win-loss-streaks data))))))
  (testing "with complete data set"
    (let [streaks (exo/serena-williams-win-loss-streaks williams-data/serena-williams-2015)]
      (is (= (count streaks)
             (count williams-data/serena-williams-2015)))
      (is (every? string? (map :current-streak streaks))))))

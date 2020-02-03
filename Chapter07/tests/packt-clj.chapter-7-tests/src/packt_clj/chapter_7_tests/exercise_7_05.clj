(ns packt-clj.chapter-7-tests.exercise-7-05
  (:require
   [packt-clj.chapter-7-tests.exercise-7-03 :as exo-three]
   [packt-clj.chapter-7-tests.exercise-7-04 :as exo-four]))


(defn take-matches
  ([limit tree]
   (take-matches limit tree identity))
  ([limit tree f]
   (cond (zero? limit)
         '()
         (= 1 limit)
         (f (first tree))
         :else
         (cons
          (f (first tree))
          (cons
           [(take-matches (dec limit) (first (second tree)) f)
            (take-matches (dec limit) (second (second tree)) f)]
           '())))))

(defn matches-with-ratings [limit tree]
  (take-matches limit
                tree
                (fn [match]
                  (-> match
                      (update :winner_rating int)
                      (update :loser_rating int)
                      (select-keys [:winner_name
                                    :loser_name
                                    :winner_rating
                                    :loser_rating])
                      (assoc :winner_probability_percentage
                             (->> (exo-three/match-probability
                                    (:winner_rating match)
                                    (:loser_rating match))
                                  (* 100)
                                  (int)))))))



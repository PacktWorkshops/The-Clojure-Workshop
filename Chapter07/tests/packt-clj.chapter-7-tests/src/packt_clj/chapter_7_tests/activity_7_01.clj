(ns packt-clj.chapter-7-tests.activity-7-01
  (:require
   [packt-clj.chapter-7-tests.exercise-7-03 :as exo-three]
   [packt-clj.chapter-7-tests.exercise-7-05 :as exo-five]))

(defn focus-history [tree player-slug focus-depth opponent-depth f]
  (cond (zero? focus-depth)
        '()
        (= 1 focus-depth)
        (f (first tree))
        :else
        (cons
         (f (first tree))
         (cons
          [(if (exo-three/player-in-match? (ffirst (second tree)) player-slug)
             (focus-history (first (second tree))
                            player-slug
                            (dec focus-depth)
                            opponent-depth
                            f)
             (exo-five/take-matches opponent-depth (first (second tree)) f))
           (if (exo-three/player-in-match? (first (second (second tree))) player-slug)
             (focus-history (second (second tree))
                            player-slug
                            (dec focus-depth)
                            opponent-depth
                            f)
             (exo-five/take-matches opponent-depth (second (second tree)) f))]
          '()))))


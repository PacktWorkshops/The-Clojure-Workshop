(def temperature-by-day
  [18 23 24 23 27 24 22 21 21 20 32 33 30 29 35 28 25 24 28 29 30])

;;; In REPL
(map (fn [today yesterday] 
       (cond (> today yesterday) :warmer
             (< today yesterday) :colder
             (= today yesterday) :unchanged))
     (rest temperature-by-day)
     temperature-by-day)



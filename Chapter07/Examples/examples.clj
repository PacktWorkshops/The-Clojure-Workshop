;;; In REPL: the simplest lazy sequence
(defn iterate-range [] (iterate inc 0))
(take 5 (iterate-range))

(defn our-range [n]
  (lazy-seq
    (cons n (our-range (inc n)))))
(take 5 (our-range 0))



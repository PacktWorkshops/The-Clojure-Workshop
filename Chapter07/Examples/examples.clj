;;; In REPL: the simplest lazy sequence

(defn iterate-range []
  (iterate inc 0))

(take 5 (iterate-range))
;; => (0 1 2 3 4)

(defn our-range [n]
  (lazy-seq
   (cons n (our-range (inc n)))))

(take 5 (our-range 0))
;; => (0 1 2 3 4)

(ns packt-clj.testing-randomness)

;;; In REPL
(def random-ints (doall
                   (take 10000000
                         (repeatedly (partial rand-int 1000)))))

;;; In REPL
(defn int-count [i xs]
  (count (filter #(= % i) xs)))

;;; In REPL
(map #(int-count % random-ints) [0 1 2 45 788  500  999 ])

;;; In REPL
(time (doall (map #(int-count % random-ints) [0 1 2 45 788  500 999])))

;;; In REPL
(time (doall (pmap #(int-count % random-ints) [0 1 2 45 788  500  999])))






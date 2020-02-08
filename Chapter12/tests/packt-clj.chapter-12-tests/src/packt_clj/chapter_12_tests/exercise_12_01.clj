(ns packt-clj.chapter-12-tests.exercise-12-01)


(def random-ints (doall
                   (take 100000         ; shorter than exercise to avoid long tests
                         (repeatedly (partial rand-int 1000)))))

(defn int-count [i xs]
  (count (filter #(= % i) xs)))


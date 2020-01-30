(ns packt-clj.chapter-4-tests.exercise-4-05)

(defn some-random-integers [size]
    (take size (repeatedly (fn [] (rand-int 100)))))


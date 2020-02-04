(ns packt-clj.chapter-11-tests.exercise-11-01)

(defmacro and-ors [& or-exps]
  (let [groups (remove (partial = '(|)) (partition-by (partial = '|) or-exps))]
    `(and
       ~@(map (fn [g] `(or ~@g)) groups))))



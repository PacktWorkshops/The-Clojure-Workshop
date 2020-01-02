(ns packt-clj.ch-11.and-ors)

(defmacro and-ors [& or-exps]
  (let [groups (remove (partial = '(|)) (partition-by (partial = '|) or-exps))]
    `(and
       ~@(map (fn [g] `(or ~@g)) groups))))




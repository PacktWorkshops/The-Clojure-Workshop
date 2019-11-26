
;;; In REPL: doseq, Clojure's most procedural loop
(doseq [n (range 5)]
    (println (str "Line " n)))

(doseq [n (range 5)]
 (when (odd? n)
  (println (str "Line " n))))

(doseq [n (filter odd? (range 5))]
    (println (str "Line " n)))


;;; In REPL: Looping shortcuts
(take 5 (repeat "myself"))

(zipmap [:score :hits :friends :level :energy :boost] (repeat 0))

(take 10 (repeatedly (partial rand-int 100)))

(repeatedly 10 (partial rand-int 100))

(defn savings [principal yearly-rate]
  (let [monthly-rate (+ 1 (/ yearly-rate 12))]
    (iterate (fn [p] (* p monthly-rate)) principal)))

(take 13 (savings 1000 0.01))

;;; In REPL: Recursion at its simplest
(defn recursive-sum [so-far numbers]
 (if (first numbers)
  (recursive-sum
   (+ so-far (first numbers))
   (next numbers))
  so-far))

;;; In REPL: When to use recur
(defn recursive-sum [so-far numbers]
  (if (first numbers)
    (recursive-sum
      (+ so-far (first numbers))
      (next numbers))
    so-far))

(defn safe-recursive-sum [so-far numbers]
  (if (first numbers)
    (recur
      (+ so-far (first numbers))
      (next numbers))
    so-far))

;;; In REPL: loop
(def process identity)

(defn grocery-verification [input-items]
  (loop [remaining-items input-items
         processed-items []]
    (if (not (seq remaining-items))
      processed-items
      (recur (next remaining-items)
             (conj processed-items (process (first remaining-items)))))))

;;; In REPL: tail recursion
(defn less-naive-tree-sum [so-far x]
  (cond (not x) so-far
        (integer? (first x)) (less-naive-tree-sum (+ so-far (first x)) (next x))
        (or (seq? (first x)) (vector? (first x)))
        (less-naive-tree-sum (less-naive-tree-sum so-far (first x)) (next x))))






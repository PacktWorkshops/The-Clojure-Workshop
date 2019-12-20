
;;; In REPL: Find both minimum and maximum 
(reduce (fn [{:keys [minimum maximum]} new-number]
          {:minimum (if (and minimum (> new-number minimum))
                      minimum
                      new-number)
           :maximum (if (and maximum (< new-number maximum))
                      maximum
                      new-number)})
        {}          ;; <---- The new argument!
        [5 23 5004 845 22])


;;; In REPL: Partitioning examples
(partition 3 [1 2 3 4 5 6 7 8 9 10])
(partition-all 3 [1 2 3 4 5 6 7 8 9 10])
(partition-by #(> % 10) [5 33 18 0 23 2 9 4 3 99])

;;; In REPL: sub-segments whose sum is less than 20
(reduce (fn [{:keys [segments current] :as accum} n]
          (let [current-with-n (conj current n)
                total-with-n (apply + current-with-n)]
            (if (> total-with-n 20)
              (assoc accum 
                     :segments (conj segments current)
                     :current [n])
              (assoc accum :current current-with-n))))
        {:segments [] :current []}
        [4 19 4 9 5 12 5 3 4 1 1 9 5 18])

;;; In REPL: improved sub-segments by sum
(defn segment-by-sum [limit ns]
        (let [result (reduce (fn [{:keys [segments current] :as accum} n]
                               (let [current-with-n (conj current n)
                                     total-with-n (apply + current-with-n)]
                                 (if (> total-with-n limit)
                                   (assoc accum 
                                          :segments (conj segments current)
                                          :current [n])
                                   (assoc accum :current current-with-n))))
                             {:segments [] :current []}
                             ns)]
          (conj (:segments result) (:current result))))

;;; In REPL: Looking back with reduce
(def numbers [4 9 2 3 7 9 5 2 6 1 4 6 2 3 3 6 1])

(defn parity-totals [ns]
  (:ret 
   (reduce (fn [{:keys [current] :as acc} n]
             (if (and (seq current)
                      (or (and (odd? (last current)) (odd? n))
                          (and (even? (last current)) (even? n))))
               (-> acc
                   (update :ret conj [n (apply + current)])
                   (update :current conj n))
               (-> acc
                   (update :ret conj [n 0])
                   (assoc :current [n]))))
           {:current [] :ret []}
           ns)))

;;; In REPL: maps to sequences and back again
(def letters-and-numbers {:a 5 :b 18 :c 35})
(reduce (fn [acc k]
                (assoc acc k (* 10 (get letters-and-numbers k))))
              {}
              (keys letters-and-numbers))
(into {} (map (fn [[k v]] [k (* v 10)]) letters-and-numbers))


;;; In REPL: group-by
(def dishes
  [{:name "Carrot Cake"
    :course :dessert}
   {:name "French Fries"
    :course :main}
   {:name "Celery"
    :course :appetizer}
   {:name "Salmon"
    :course :main}
   {:name "Rice"
    :course :main}
   {:name "Ice Cream"
    :course :dessert}])

(defn our-group-by [f xs]
  (reduce (fn [acc x]
            (update acc (f x) (fn [sublist] (conj (or sublist []) x))))
          {}
          xs))


(ns packt-clj.grocery-partitioning)


(defn full-bag? [items]
 (let [weight (apply + (map :weight items))
    size (apply + (map :max-dimension items))]
  (or (> weight 3200)
    (> size 800))))


(defn robust-bag-sequences* [{:keys [current-bag bags] :as acc} stream]
 (cond
  (not stream)
  (conj bags current-bag)
  (full-bag? (conj current-bag (first stream)))
  (recur (assoc acc
         :current-bag [(first stream)]
         :bags (conj bags current-bag))
      (next stream))
  :otherwise-bag-not-full
  (recur (assoc acc :current-bag (conj current-bag (first stream)))
      (next stream))))


(defn robust-bag-sequences [stream]
 (robust-bag-sequences* {:bags []
             :current-bag []} stream))





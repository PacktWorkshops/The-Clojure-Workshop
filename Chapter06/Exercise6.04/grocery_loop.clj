(ns packt-clj.grocery-loop)

(defn looping-robust-bag-sequences [stream]
  (loop [remaining-stream stream
         acc {:current-bag []
              :bags []}]
    (let [{:keys [current-bag bags]} acc]
      (cond (not remaining-stream)
            (conj bags current-bag)
            (full-bag? (conj current-bag (first remaining-stream)))
            (recur (next remaining-stream)
                   (assoc acc
                          :current-bag [(first remaining-stream)]
                          :bags (conj bags current-bag)))
            :otherwise-bag-not-full
            (recur (next remaining-stream)
                   (assoc acc :current-bag (conj current-bag (first remaining-stream)))))))) 



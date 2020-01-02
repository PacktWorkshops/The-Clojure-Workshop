(ns packt-clj.monitored)

(defn wrap-fn-body [fn-name tx-fn b]
  (let [arg-list (first b)
        fn-body (rest b)]
    (when-not (first (filter #(= % 'client-id) arg-list))
      (throw (ex-info "Missing client-id argument" {})))
    `(~arg-list
      (let [start-time# (System/nanoTime)]
        (try
          (let [result# (do  ~@fn-body)]
            (~tx-fn {:name ~(name fn-name)
                     :client-id ~'client-id
                     :status :complete
                     :start-time start-time#
                     :end-time (System/nanoTime)})
            result#)
          (catch Exception e#
            (~tx-fn {:name ~(name fn-name)
                     :client-id ~'client-id
                     :status :error
                     :start-time start-time#
                     :end-time (System/nanoTime)})
            (throw e#)))))))

(defmacro defmonitored
  [fn-name tx-fn & args-and-body]
  (let [pre-arg-list (take-while (complement sequential?) args-and-body)
        fn-content (drop-while (complement sequential?) args-and-body)
        fn-bodies (if (vector? (first fn-content))
                    `(~fn-content)
                    fn-content)]
    `(defn ~fn-name ~@pre-arg-list
       ~@(map (partial wrap-fn-body fn-name tx-fn) fn-bodies))))


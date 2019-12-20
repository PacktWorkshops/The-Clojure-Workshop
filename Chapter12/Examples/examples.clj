
;;; Futures

;;; In REPL
(defn expensive-calc-1 [i]
  (Thread/sleep (+ 500 (rand 1000)))
  (println "Calc 1")
  (+ i 5))

;;; In REPL
(+ (expensive-calc-1 10) (expensive-calc-2 25))

;;; In REPL
(let [c1 (future (expensive-calc-1 10))
      c2 (future (expensive-calc-2 20))]
  (+ (deref c1) (deref c2)))

;;; In REPL
(let [c1 (future (expensive-calc-1 10))
      c2 (future (expensive-calc-2 20))]
  (+ (deref c1) (deref c2)))


;;; Atoms

;;; In REPL
(def integer-atom (atom 5))
(deref integer-atom)
(swap! integer-atom inc)

;;; In REPL
(do
  (future (do (Thread/sleep 500) (swap! integer-atom + 500)))
  (future (swap! integer-atom * 1000))
  (deref integer-atom))

;;; In REPL
(do
  (def integer-atom (atom 6))
  (future (swap! integer-atom + 500))
  (future (do (Thread/sleep 500) (swap! integer-atom * 1000)))
  (deref integer-atom))

;;; Retries

;;; In REPL
(do
  (def integer-atom (atom 5))
  (future (swap! integer-atom (fn [a] (Thread/sleep 2000)(* a 100))))
  (future (swap! integer-atom (fn [a] (Thread/sleep 500) (+ a 200))))
  @integer-atom)

;;; Refs

;;; In REPL
(do
  (reset-accounts)
  (future (buy-acme-corp-shares 1))
  (future (dosync
            (Thread/sleep 300)
            (alter acme-corp-share-price + 10))))


;;; Agents

;;; In REPL
(def integer-agent (agent 5))
(send integer-agent (fn [a] (Thread/sleep 5000) (inc a)))
(send integer-agent (fn [a] (Thread/sleep 5000) (inc a)))
@integer-agent

;;; In REPL (compare agent to atom)
(def integer-atom (atom 5))
(swap! integer-atom (fn [a] (Thread/sleep 5000) (inc a)))
(swap! integer-atom (fn [a] (Thread/sleep 5000) (inc a)))

;;; Watchers

;;; In REPL
(def observable-atom (atom 5))
(add-watch observable-atom :watcher-1
           (fn [k a old new]
             (println "The observable atom has gone from" old "to" new)))

(swap! observable-atom inc)









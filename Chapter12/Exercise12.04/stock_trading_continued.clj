;;; From Exercise 12.03

;;; In REPL
(def client-account (ref 2100))
(def broker-account (ref 10000))
(def acme-corp-share-price (ref 22))
(def broker-stocks (ref {:acme-corp 50}))
(def client-stocks (ref {:acme-corp 0}))

;;; In REPL
(defn reset-accounts []
  (dosync
    (ref-set acme-corp-share-price 22)
    (ref-set client-account 2100)
    (ref-set broker-account 10000)
    (ref-set client-stocks {:acme-corp 0})
    (ref-set broker-stocks {:acme-corp 50})))


;;; New code for Exercise 12.04

;;; In REPL
(defn buy-acme-corp-shares [n]
  (dosync
    (let [price (ensure acme-corp-share-price)]
      (println "Let's buy" n "stock(s) at" price "per stock")
      (Thread/sleep 1000)
      (alter client-account #(- % price))
      (alter broker-account #(+ % price))
      (alter client-stocks update :acme-corp #(+ % n))
      (alter broker-stocks update :acme-corp #(- % n)))))


;;; In REPL
(do
  (reset-accounts)
  (future (buy-acme-corp-shares 1))
  (future (dosync
            (Thread/sleep 300)
            (println "Raising share price to " (+ @acme-corp-share-price 10))
            (alter acme-corp-share-price + 10))))


;;; In REPL
(defn buy-acme-corp-shares [n]
  (dosync
    (let [price @acme-corp-share-price]
      (println "Let's buy" n "stock(s) at" price "per stock")
      (Thread/sleep 1000)
      (alter acme-corp-share-price identity)
      (alter client-account #(- % price))
      (alter broker-account #(+ % price))
      (alter client-stocks update :acme-corp #(+ % n))
      (alter broker-stocks update :acme-corp #(- % n)))))

;;; In REPL
(do
  (reset-accounts)
  (future (buy-acme-corp-shares 1))
  (future (dosync
            (Thread/sleep 300)
            (println "Raising share price to " (+ @acme-corp-share-price 10))


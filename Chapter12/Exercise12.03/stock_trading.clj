;;; In REPL
(def client-account (ref 2100))
(def broker-account (ref 10000))
(def acme-corp-share-price (ref 22))
(def broker-stocks (ref {:acme-corp 50}))
(def client-stocks (ref {:acme-corp 0}))

;;; In REPL
(defn buy-acme-corp-shares [n]
  (dosync
    (let [purchase-price (* n @acme-corp-share-price)]
      (alter client-account #(- % purchase-price))
      (alter broker-account #(+ % purchase-price))
      (alter client-stocks update :acme-corp #(+ % n))
      (alter broker-stocks update :acme-corp #(- % n)))))

;;; In REPL
(alter client-stocks update :acme-corp + n)
(alter broker-stocks update :acme-corp - n)


;;; In REPL
(defn buy-acme-corp-shares [n]
  (dosync
    (let [purchase-price (* n @acme-corp-share-price)]
      (println "Let's buy" n "stock(s) at" purchase-price "per stock")
      (Thread/sleep 1000)
      (alter client-account #(- % purchase-price))
      (alter broker-account #(+ % purchase-price))
      (alter client-stocks update :acme-corp #(+ % n))
      (alter broker-stocks update :acme-corp #(- % n)))))

;;; In REPL
(defn reset-accounts []
  (dosync
    (ref-set acme-corp-share-price 22)
    (ref-set client-account 2100)
    (ref-set broker-account 10000)
    (ref-set client-stocks {:acme-corp 0})
    (ref-set broker-stocks {:acme-corp 50})))

;;; In REPL
(do
  (reset-accounts)
  (future (buy-acme-corp-shares 1))
  (future (dosync
            (Thread/sleep 300)
            (alter client-account + 500))))

;;; In REPL
(do
  (reset-accounts)
  (future (buy-acme-corp-shares 1))
  (future (dosync
            (Thread/sleep 300)
            (alter client-account + 500)))
  (future (dosync
            (Thread/sleep 350)
            (alter broker-account - 200)))
  (future (dosync
            (Thread/sleep 600)
            (alter broker-account + 1200))))


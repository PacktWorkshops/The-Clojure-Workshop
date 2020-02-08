(ns packt-clj.chapter-12-tests.exercise-12-03-test
  (:require  [clojure.test :as t :refer [deftest is testing]]))

(deftest buy-acme-corp-shares-step-3
  (let [client-account (ref 2100)
        broker-account (ref 10000)
        acme-corp-share-price (ref 22)
        broker-stocks (ref {:acme-corp 50})
        client-stocks (ref {:acme-corp 0})

        buy-acme-corp-shares
        (fn [n]
          (dosync
            (let [purchase-price (* n @acme-corp-share-price)]
              (alter client-account #(- % purchase-price))
              (alter broker-account #(+ % purchase-price))
              (alter client-stocks update :acme-corp #(+ % n))
              (alter broker-stocks update :acme-corp #(- % n)))))]

    (is (= {:acme-corp 49} (buy-acme-corp-shares 1)))
    (is (= 2078 @client-account))
    (is (= 10022 @broker-account))
    (is (= {:acme-corp 49} @broker-stocks))
    (is (= {:acme-corp 1} @client-stocks))))


(deftest buy-acme-corp-shares-step-8
  (let [client-account (ref 2100)
        broker-account (ref 10000)
        acme-corp-share-price (ref 22)
        broker-stocks (ref {:acme-corp 50})
        client-stocks (ref {:acme-corp 0})

        buy-acme-corp-shares
        (fn [n]
            (dosync
              (let [purchase-price (* n @acme-corp-share-price)]
                (println "Let's buy" n "stock(s) at" purchase-price "per stock")
                (Thread/sleep 1000)
                (alter client-account #(- % purchase-price))
                (alter broker-account #(+ % purchase-price))
                (alter client-stocks update :acme-corp #(+ % n))
                (alter broker-stocks update :acme-corp #(- % n)))))

        buy-shares (future (buy-acme-corp-shares 1))
        get-money (future (dosync
                            (Thread/sleep 300)
                            (alter client-account + 500)))]
    (is (=  {:acme-corp 49} @buy-shares))
    (is (= 2600 @get-money))
    (is (= 2578 @client-account) "Both transactions are correctly recorded.")))

(deftest buy-acme-corp-shares-step-11
  (let [client-account (ref 2100)
        broker-account (ref 10000)
        acme-corp-share-price (ref 22)
        broker-stocks (ref {:acme-corp 50})
        client-stocks (ref {:acme-corp 0})

        buy-acme-corp-shares
        (fn [n]
            (dosync
              (let [purchase-price (* n @acme-corp-share-price)]
                (println "Let's buy" n "stock(s) at" purchase-price "per stock")
                (Thread/sleep 1000)
                (alter client-account #(- % purchase-price))
                (alter broker-account #(+ % purchase-price))
                (alter client-stocks update :acme-corp #(+ % n))
                (alter broker-stocks update :acme-corp #(- % n)))))

        buy-shares (future (buy-acme-corp-shares 1))
        get-money (future (dosync
                            (Thread/sleep 300)
                            (alter client-account + 500)))
        broker-spends-money (future (dosync
                                      (Thread/sleep 350)
                                      (alter broker-account - 200)))

        broker-receives-money (future (dosync
                                        (Thread/sleep 600)
                                        (alter broker-account + 1200)))]
    (is (=  {:acme-corp 49} @buy-shares))
    (is (= 2600 @get-money))
    (is (= (- 10000 200) @broker-spends-money))
    (is (= (+ 9800 1200) @broker-receives-money))
    (is (= 2578 @client-account) "Both transactions are correctly recorded.")))






(ns packt-clj.chapter-12-tests.exercise-12-04-test
  (:require  [clojure.test :as t :refer [deftest is testing]]))

(deftest buy-acme-corp-shares-step-2
  (let [client-account (ref 2100)
        broker-account (ref 10000)
        acme-corp-share-price (ref 22)
        broker-stocks (ref {:acme-corp 50})
        client-stocks (ref {:acme-corp 0})

        buy-acme-corp-shares
        (fn [n]
          (dosync
            (let [price (ensure acme-corp-share-price)]
              (println "Let's buy" n "stock(s) at" price "per stock")
              (Thread/sleep 1000)
              (alter client-account #(- % price))
              (alter broker-account #(+ % price))
              (alter client-stocks update :acme-corp #(+ % n))
              (alter broker-stocks update :acme-corp #(- % n)))))

        buy-shares (future (buy-acme-corp-shares 1))
        change-price  (future (dosync
                                (Thread/sleep 300)
                                (println "Raising share price to " (+ @acme-corp-share-price 10))
                                (alter acme-corp-share-price + 10)))]
    (is (= {:acme-corp 49} @buy-shares))
    (is (= 32 @change-price))
    (is (= 2078 @client-account))))


(deftest buy-acme-corp-shares-step-4
  (let [client-account (ref 2100)
        broker-account (ref 10000)
        acme-corp-share-price (ref 22)
        broker-stocks (ref {:acme-corp 50})
        client-stocks (ref {:acme-corp 0})

        buy-acme-corp-shares
        (fn [n]
          (dosync
            (let [price @acme-corp-share-price]
              (println "Let's buy" n "stock(s) at" price "per stock")
              (Thread/sleep 1000)
              (alter acme-corp-share-price identity)
              (alter client-account #(- % price))
              (alter broker-account #(+ % price))
              (alter client-stocks update :acme-corp #(+ % n))
              (alter broker-stocks update :acme-corp #(- % n)))))

        buy-shares (future (buy-acme-corp-shares 1))
        change-price (future (dosync
                               (Thread/sleep 300)
                               (println "Raising share price to " (+ @acme-corp-share-price 10))
                               (alter acme-corp-share-price + 10)))]

    (is (= {:acme-corp 49} @buy-shares))
    (is (= 32 @change-price))
    (is (= 2068 @client-account))))

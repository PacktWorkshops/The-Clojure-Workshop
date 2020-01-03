(ns books-app.core
  (:require [books-app.utils :as utils])
  (:import [java.util Scanner])
  (:gen-class))

(def input (Scanner. System/in))

(def ^:const orders-file "orders.edn")

(def ^:const books {:2019 {:clojure {:title "Hands-On Reactive Programming with Clojure" :price 20}
                           :go      {:title "Go Cookbook" :price 18}}
                    :2018 {:clojure {:title "Clojure Microservices" :price 15}
                           :go {:title "Advanced Go programming" :price 25}}})

(defn buy-book [year prog-lang]
       (println "How many books do you want to buy?")
       (let [choice (.nextInt input)
             price (utils/calculate-book-price (get books year) prog-lang choice)]
            (utils/save-book-order orders-file year prog-lang choice price)
            (utils/display-bought-book-message (:title (get (get books year) prog-lang)) choice price)))

(defn show-year-menu [year]
       (let [year-books (get books year)]
            (println "| Books in" (name year) "|")
            (println "| 1. " (:title (:clojure year-books)) " 2. " (:title (:go year-books))  "|")
            (let [choice (.nextInt input)]
                 (case choice
                       1 (buy-book year :clojure)
                       2 (buy-book year :go)))))

(defn show-menu []
       (println "| Available books by year |")
       (println "|1. 2019   2. 2018 |")
       (let [choice (.nextInt input)]
            (case choice
                  1 (show-year-menu :2019)
                  2 (show-year-menu :2018))))

(defn show-orders-by-year [year]
       (println "\n")
       (doseq [order (filter #(= year (:year %)) (utils/load-orders orders-file))]
              (println (utils/display-order order books))))

(defn show-orders []
      (println "| Books by publish year |")
      (println "|1. 2019   2. 2018 |")
      (let [choice (.nextInt input)]
           (case choice
                 1 (show-orders-by-year :2019)
                 2 (show-orders-by-year :2018))))

(defn start-app []
       "Displaying main menu and processing user choices."
       (let [run-application (ref true)]
            (while (deref run-application)
                   (println "\n|     Books app         |")
                   (println "| 1-Menu 2-Orders 3-Exit |\n")
                   (let [choice (.nextInt input)]
                        (case choice
                              1 (show-menu)
                              2 (show-orders)
                              3 (dosync (alter run-application (fn [_] false))))))))


(defn -main
  "Main function to run the app."
  [& args]
  (start-app))

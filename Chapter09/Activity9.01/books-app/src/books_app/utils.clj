(ns books-app.utils
    (:require [clojure.java.io :as io])
    (:import [java.io PushbackReader]))


;;; files saving and loading functions

(defn save-to [location data]
      (spit location data :append true))

(defn file-exists [location]
      (.exists (io/as-file location)))

(defn read-one-order
      [r]
      (try
        (read r)
        (catch java.lang.RuntimeException e
          (if (= "EOF while reading" (.getMessage e))
            ::EOF
            (throw e)))))

(defn load-orders
      "Reads a sequence of orders in file at path."
      [file]
      (if (file-exists file)
        (with-open [r (PushbackReader. (io/reader file))]
                   (binding [*read-eval* false]
                            (doall (take-while #(not= ::EOF %) (repeatedly #(read-one-order r))))))
        []))

;;; orders calculating and formatting function

(defn calculate-book-price [books title number]
      (->
        (get books title)
        :price
        (* number)
        float))

(defn display-order [order books]
      (str "Bought " (:number order) ": " (:title (get (get books (:year order)) (:prog-lang order))) " published in " (name (:year order)) " for €" (:price order)))

(defn display-bought-book-message [title number total]
      (println "Buying" number title "for total:€" total))

(defn save-book-order [orders-file year prog-lang number price]
      (save-to orders-file {:year year :prog-lang prog-lang :number number :price price}))
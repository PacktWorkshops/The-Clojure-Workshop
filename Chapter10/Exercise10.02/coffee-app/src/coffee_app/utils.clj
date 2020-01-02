(ns coffee-app.utils
    (:require [clojure.java.io :as io])
    (:import [java.io PushbackReader]))


;;; files saving and loading functions

(defn save-to [location data]
      (spit location data :append true))

(defn file-exists? [location]
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
      (if (file-exists? file)
        (with-open [r (PushbackReader. (io/reader file))]
                   (binding [*read-eval* false]
                            (doall (take-while #(not= ::EOF %) (repeatedly #(read-one-order r))))))
        []))

;;; orders calculating and formatting function
(defn calculate-coffee-price [coffees coffee-type number]
      (->
        (get coffees coffee-type)
        (* number)
        float
        Math/abs))

(defn display-order [order]
      (str "Bought " (:number order) " cups of " (name (:type order)) " for €" (:price order)))

(defn display-bought-coffee-message [type number total]
      (println "Buying" number (name type) "coffees for total:€" total))

(defn save-coffee-order [orders-file type number price]
      (save-to orders-file {:type type :number number :price price}))
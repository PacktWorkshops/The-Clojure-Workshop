(ns coffee-app.utils)

;;; orders calculating and formatting function

(defn calculate-coffee-price [coffees coffee-type number]
      (->
        (get coffees coffee-type)
        (* number)
        float))

(defn display-bought-coffee-message [type number total]
      (str "Buying " number " " (name type) " coffees for total:€" total))
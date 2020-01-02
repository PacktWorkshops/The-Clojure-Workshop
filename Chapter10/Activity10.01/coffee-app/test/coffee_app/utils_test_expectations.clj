(ns coffee-app.utils-test-expectations
    (:require [coffee-app.utils :refer :all]
              [expectations :refer [expect side-effects]]))

(expect "Bought 4 cups of latte for €3.8" (display-order {:number 4 :price 3.8 :type :latte}))

(expect "Bought 7 cups of espresso for €6.3" (display-order {:number 7 :price 6.3 :type :espresso}))

(expect String (display-order {:number 7 :price 6.3 :type :espresso}))

(expect #"Bought 7 cups" (display-order {:number 7 :price 6.3 :type :espresso}))

(expect #"cups of espresso" (display-order {:number 7 :price 6.3 :type :espresso}))

(expect #"for €6.3" (display-order {:number 7 :price 6.3 :type :espresso}))


(expect true (file-exists "/tmp"))

(expect false (file-exists "no-file"))

(expect Boolean (file-exists "etc"))


(expect [["/tmp/orders.edn" :latte 1 2.4]
         ["/tmp/orders.edn" :latte 2 3.9]]
        (side-effects [save-coffee-order]
                      (save-coffee-order "/tmp/orders.edn" :latte 1 2.4)
                      (save-coffee-order "/tmp/orders.edn" :latte 2 3.9)))

(expect [["/tmp/coffees.edn" {:type :latte :number 1 :price 2.4}]
         ["/tmp/coffees.edn" {:type :latte :number 2 :price 3.9}]]
        (side-effects [save-to]
                      (save-coffee-order "/tmp/coffees.edn" :latte 1 2.4)
                      (save-coffee-order "/tmp/coffees.edn" :latte 2 3.9)))

(expect [["/tmp/menu.edn" {:type :latte :number 1 :price 2.4} :append true]
         ["/tmp/menu.edn" {:type :latte :number 3 :price 4.7} :append true]]
        (side-effects [spit]
                      (save-to "/tmp/menu.edn" {:type :latte :number 1 :price 2.4})
                      (save-to "/tmp/menu.edn" {:type :latte :number 3 :price 4.7})))

(expect [] (load-orders "/tmp/data.edn"))
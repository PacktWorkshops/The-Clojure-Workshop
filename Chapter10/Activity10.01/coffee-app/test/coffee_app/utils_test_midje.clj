(ns coffee-app.utils-test-midje
    (:require [coffee-app.utils :refer :all]
              [midje.sweet :refer :all]))

(facts "Passing an order should return display message"
       (fact (display-order {:number 4 :price 3.8 :type :latte}) => "Bought 4 cups of latte for €3.8")
       (fact (display-order {:number 7 :price 6.3 :type :espresso}) => "Bought 7 cups of espresso for €6.3"))

(facts "Returned message should match regular expression"
       (fact (display-order {:number 7 :price 6.3 :type :espresso}) => #"Bought 7 cups")
       (fact (display-order {:number 7 :price 6.3 :type :espresso}) => #"cups of espresso")
       (fact (display-order {:number 7 :price 6.3 :type :espresso}) => #"for €6.3"))


(facts "True should be returned when a file exists"
       (fact (file-exists "/tmp") => true)
       (fact (file-exists "/etc") => true))

(facts "False should be returned when a file does not exist"
       (fact (file-exists "no-file") => false)
       (fact (file-exists "missing-file") => false))

(facts "Empty vector should be returned when there is no orders file"
       (fact (load-orders "/tmp/data.edn") => [])
       (fact (load-orders "/tmp/no-data.edn") => []))
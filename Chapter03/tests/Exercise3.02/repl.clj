(require '[clojure.test :as test :refer [is are deftest run-tests]])

(def booking
  {
    :id 8773
    :customer-name "Alice Smith"
    :catering-notes "Vegetarian on Sundays"
    :flights [
      {
        :from {:lat 48.9615 :lon 2.4372 :name "Paris Le Bourget Airport"},
        :to {:lat 37.742 :lon -25.6976 :name "Ponta Delgada Airport"}},
      {
        :from {:lat 37.742 :lon -25.6976 :name "Ponta Delgada Airport"},
        :to {:lat 48.9615 :lon 2.4372 :name "Paris Le Bourget Airport"}}
    ]
  })


; (deftest exercise302-test
;  

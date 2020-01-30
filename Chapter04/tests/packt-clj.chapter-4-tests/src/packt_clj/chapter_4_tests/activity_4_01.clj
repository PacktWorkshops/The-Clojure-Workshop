(ns packt-clj.chapter-4-tests.activity-4-01
  (:require  [clojure.test :as t]))



(defn max-value-by-status [field status users]
               (->> 
                 users
                  ;; step 1: use filter to only keep users who
                  ;; have the status we are looking for 
                 (filter #(= (:status %) status))
                 ;; step 2: field is a keyword, so we can use it as 
                 ;; a function when calling map.
                 (map field)
                 ;; step 3: use the apply max pattern, with a default
                 (apply max 0)))

(defn min-value-by-status [field status users]
               (->> 
                 users
                 (filter #(= (:status %) status))
                 (map field)
                 (apply min 0)))

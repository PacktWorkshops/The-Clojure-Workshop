(def base-co2 382)
(def base-year 2006)

(defn co2-estimate
 "Returns a year's estimate of carbon dioxide parts per million in the atmosphere"
 [year]
 (let [year-diff (- year base-year)]
  (+ base-co2 (* 2 year-diff))))

(co2-estimate 2006)
(co2-estimate 2020)
(co2-estimate 2050)
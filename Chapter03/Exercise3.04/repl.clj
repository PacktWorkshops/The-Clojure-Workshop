;; 1
(def weapon-fn-map
  {:fists (fn [health] (if (< health 100) (- health 10) health))})

;; 2
((weapon-fn-map :fists) 50)
((weapon-fn-map :fists) 150)

;; 3
(def weapon-fn-map
  {
    :fists (fn [health] (if (< health 100) (- health 10) health))
    :staff (partial + 35)
  })

;; 4
((weapon-fn-map :staff) 150)

;; 5 
(def weapon-fn-map
  {
    :fists (fn [health] (if (< health 100) (- health 10) health))
    :staff (partial + 35)
    :sword #(- % 100)
  })

;; 6
((weapon-fn-map :sword) 150)

;; 7
(def weapon-fn-map
  {
    :fists (fn [health] (if (< health 100) (- health 10) health))
    :staff (partial + 35)
    :sword #(- % 100)
    :cast-iron-saucepan #(- % 100 (rand-int 50))
  })

;; 8
((weapon-fn-map :cast-iron-saucepan) 200)

;; 9
; (source identity)
; (defn identity
;   "Returns its argument."
;   {:added "1.0"
;    :static true}
;   [x] x)
; nil

;; 10
(def weapon-fn-map
  {
    :fists (fn [health] (if (< health 100) (- health 10) health))
    :staff (partial + 35)
    :sword #(- % 100)
    :cast-iron-saucepan #(- % 100 (rand-int 50))
    :sweet-potato identity
  })

;; 11
(defn strike
  "With one argument, strike a target with a default :fists `weapon`. With two argument, strike a target with `weapon` and return the target entity"
  ([target] (strike target :fists))
  ([target weapon]
    (let [weapon-fn (weapon weapon-fn-map)]
      (update target :health weapon-fn))))

;; 12
(def enemy {:name "Arnold", :health 250})
(strike enemy :sweet-potato)
(strike enemy :sword)
(strike enemy :cast-iron-saucepan)
(strike (strike enemy :sword) :cast-iron-saucepan)


;; 13
(update enemy :health (comp (:sword weapon-fn-map) (:cast-iron-saucepan weapon-fn-map)))

;; 14
(defn mighty-strike
  "Strike a `target` with all weapons!"
  [target]
  (let [weapon-fn (apply comp (vals weapon-fn-map))]
      (update target :health weapon-fn)))

;; 1
(def weapon-damage {:fists 10.0 :staff 35.0 :sword 100.0 :cast-iron-saucepan 150.0})

;; 2
(defn strike
  ([target weapon]
    (let [points (weapon weapon-damage)]
      (if (= :gnomes (:camp target))
        (update target :health + points)
        (update target :health - points)))))

;; 3
(def enemy {:name "Zulkaz", :health 250, :armor 0.8, :camp :trolls})
(strike enemy :sword)


;; 4
(def ally {:name "Carla", :health 80, :camp :gnomes})
(strike ally :staff)


;; 5
(defn strike
  ([target weapon]
    (let [points (weapon weapon-damage)]
      (if (= :gnomes (:camp target))
        (update target :health + points)
        (let [armor (or (:armor target) 0)
              damage (* points (- 1 armor))]
          (update target :health - damage))))))


;; 8
(defn strike
  ([{:keys [camp armor] :as target} weapon]
    (let [points (weapon weapon-damage)]
      (if (= :gnomes camp)
        (update target :health + points)
        (let [armor-effect (or (:armor target) 0)
              damage (* points (- 1 armor-effect))]
          (update target :health - damage))))))



;; 9
(defn strike
  "With one argument, strike a target with a default :fists `weapon`. With two argument, strike a target with `weapon`.
   Strike will heal a target that belongs to the gnomes camp."
  ([target] (strike target :fists))
  ([{:keys [camp armor], :or {armor 0}, :as target} weapon]
    (let [points (weapon weapon-damage)]
      (if (= :gnomes camp)
        (update target :health + points)
        (let [damage (* points (- 1 armor))]
          (update target :health - damage))))))


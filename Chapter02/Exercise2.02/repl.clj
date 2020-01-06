;; 1
(def favorite-fruit {:name "Kiwi", :color "Green", :kcal_per_100g 61 :distinguish_mark "Hairy"})

;; 2
(get favorite-fruit :name)
(get favorite-fruit :color)

;; 3
(get favorite-fruit :taste)
(get favorite-fruit :taste "Very good 8/10")
(get favorite-fruit :kcal_per_100g 0)

;; 4
(favorite-fruit :color)

;; 5
(:color favorite-fruit)

;; 6
(:shape favorite-fruit "egg-like")

;; 7
(assoc favorite-fruit :shape "egg-like")

;; 8
favorite-fruit

;; 9
(assoc favorite-fruit :color "Brown")

;; 10
(assoc favorite-fruit :yearly_production_in_tonnes {:china 2025000 :italy 541000 :new_zealand 412000 :iran 311000 :chile 225000})

;; 11
(assoc favorite-fruit :kcal_per_100g (- (:kcal_per_100g favorite-fruit) 1))

;; 12
(update favorite-fruit :kcal_per_100g dec)

;; 13
(update favorite-fruit :kcal_per_100g - 10)

;; 14
(dissoc favorite-fruit :distinguish_mark)
(dissoc favorite-fruit :kcal_per_100g :color)


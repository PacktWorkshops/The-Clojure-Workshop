(require '[clojure.test :as test :refer [is are deftest run-tests]])

(def favorite-fruit {:name "Kiwi", :color "Green", :kcal_per_100g 61 :distinguish_mark "Hairy"})

(deftest exercise-202-test
  (is (= "Kiwi" (get favorite-fruit :name)))
  (is (= "Green" (get favorite-fruit :color)))
  (is (= nil (get favorite-fruit :taste)))
  (is (= "Very good 8/10" (get favorite-fruit :taste "Very good 8/10")))
  (is (= 61 (get favorite-fruit :kcal_per_100g 0)))
  (is (= "Green" (favorite-fruit :color)))
  (is (= "Green" (:color favorite-fruit)))
  (is (= "egg-like" (:shape favorite-fruit "egg-like")))
  (is (= {:name "Kiwi", :color "Green", :kcal_per_100g 61, :distinguish_mark "Hairy", :shape "egg-like"}
       (assoc favorite-fruit :shape "egg-like")))
  (is (= {:name "Kiwi", :color "Green", :kcal_per_100g 61, :distinguish_mark "Hairy"}
       favorite-fruit))
  (is (= {:name "Kiwi", :color "Brown", :kcal_per_100g 61, :distinguish_mark "Hairy"}
       (assoc favorite-fruit :color "Brown")))
  (is (= {:name "Kiwi", :color "Green", :kcal_per_100g 61, :distinguish_mark "Hairy", :yearly_production_in_tonnes {:china 2025000, :italy 541000, :new_zealand 412000, :iran 311000, :chile 225000}}
       (assoc favorite-fruit :yearly_production_in_tonnes {:china 2025000 :italy 541000 :new_zealand 412000 :iran 311000 :chile 225000})))
  (is (= {:name "Kiwi", :color "Green", :kcal_per_100g 60, :distinguish_mark "Hairy"}
       (assoc favorite-fruit :kcal_per_100g (- (:kcal_per_100g favorite-fruit) 1))))
  (is (= {:name "Kiwi", :color "Green", :kcal_per_100g 60, :distinguish_mark "Hairy"}
       (update favorite-fruit :kcal_per_100g dec)))
  (is (= {:name "Kiwi", :color "Green", :kcal_per_100g 51, :distinguish_mark "Hairy"}
       (update favorite-fruit :kcal_per_100g - 10)))
  (is (= {:name "Kiwi", :color "Green", :kcal_per_100g 61}
       (dissoc favorite-fruit :distinguish_mark)))
  (is (= {:name "Kiwi", :distinguish_mark "Hairy"}
       (dissoc favorite-fruit :kcal_per_100g :color))))

(run-tests)

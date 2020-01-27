(ns packt-clj.game-values)


(def game-users
  [{:id 9342
    :username "speedy"
    :current-points 45
    :remaining-lives 2
    :experience-level 5
    :status :active}
   {:id 9854
    :username "stealthy"
    :current-points 1201
    :remaining-lives 1
    :experience-level 8
    :status :speed-boost}
   {:id 3014
    :username "sneaky"
    :current-points 725
    :remaining-lives 7
    :experience-level 3
    :status :active}
   {:id 2051
    :username "forgetful"
    :current-points 89
    :remaining-lives 4
    :experience-level 5
    :status :imprisoned}
   {:id 1032
    :username "wandering"
    :current-points 2043
    :remaining-lives 12
    :experience-level 7
    :status :speed-boost}
   {:id 7213
    :username "slowish"
    :current-points 143
    :remaining-lives 0
    :experience-level 1
    :status :speed-boost}
   {:id 5633
    :username "smarter"
    :current-points 99
    :remaining-lives 4
    :experience-level 4
    :status :terminated}
   {:id 3954
    :username "crafty"
    :current-points 21
    :remaining-lives 2
    :experience-level 8
    :status :active}
   {:id 7213
    :username "smarty"
    :current-points 290
    :remaining-lives 5
    :experience-level 12
    :status :terminated}
   {:id 3002
    :username "clever"
    :current-points 681
    :remaining-lives 1
    :experience-level 8
    :status :active}])


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

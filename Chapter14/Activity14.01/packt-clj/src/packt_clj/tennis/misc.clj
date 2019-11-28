(ns packt-clj.tennis.misc)

(defn generate-activity
  [user-id]
  (let [activity-distance-multiplier {:cycle (+ 12 (rand-int 19)) ;; 12-30km/h
                                      :run   (+ 9 (rand-int 7))   ;; 9-15km/h
                                      :swim  (+ 2 (rand-int 2))}  ;; 2-3km/h
        duration (* 60 (+ 15 (rand-int 165))) ;; 15m-3h, in seconds
        activity-type (rand-nth [:cycle :run :swim]) ;; random activity
        distance (* 1000 (activity-distance-multiplier activity-type) (/ duration 3600.))] ;; distance in metres, using duration in hours and distance multiplier
    {:activity_type (name activity-type)
     :activity_date (str (t/plus (t/local-date "2019-01-01") (t/days (rand-int 120)))) ;; random date within the first 120 days of the year
     :distance      distance
     :duration      duration
     :user_id       user-id}))


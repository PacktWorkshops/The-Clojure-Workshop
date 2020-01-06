;; 1
(def player {:name "Lea" :health 200 :position {:x 10 :y 10 :facing :north}})

;; 2
(defmulti move #(:facing (:position %)))

;; 3
(ns-unmap 'user 'move)
(defmulti move (comp :facing :position))

;; 4
(defmethod move :north
	[entity]
  (update-in entity [:position :y] inc))

;; 5
(move player)

;; 6
(defmethod move :south
	[entity]
  (update-in entity [:position :y] dec))

(defmethod move :west
	[entity]
  (update-in entity [:position :x] inc))

(defmethod move :east
	[entity]
  (update-in entity [:position :x] dec))


;; 7
(move {:position {:x 10 :y 10 :facing :west}})
(move {:position {:x 10 :y 10 :facing :south}})
(move {:position {:x 10 :y 10 :facing :east}})


;; 9
(defmethod move :default [entity] entity)


(move {:position {:x 10 :y 10 :facing :wall}})

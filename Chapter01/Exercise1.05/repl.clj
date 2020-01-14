;; 1
(fn [])

;; 2
(fn [x] (* x x))

;; 3
((fn [x] (* x x)) 2)

;; 4
(def square (fn [x] (* x x)))

;; 5
(square 2)
; (square *1)
; (square *1)

;; 6
(defn square [x] (* x x))
(square 10)

;; 7
(defn meditate [s calm]
   (println "Clojure Meditate v1.0")
   (if calm
     (clojure.string/capitalize s) 
     (str (clojure.string/upper-case s) "!")))

;; 8
(meditate "in calmness lies true pleasure" true)
(meditate "in calmness lies true pleasure" false)

;; 9
(meditate "in calmness lies true pleasure")

;; 10
(defn square
  "Returns the product of the number `x` with itself"
  [x]
  (* x x))

;; 11
(doc square)

;; 1
(def x 10)

;; 2
x

;; 3
(def x 20)
x

;; 4
(inc x)
x

;; 5
x
(do (def x 42))
x

;; 6
(let [y 3] (println y) (* 10 y))

;; 7
y

;; 8
(let [x 3] (println x))
x

;; 9
(let [x 10 y 20]  (str "x is " x " and y is " y))

;; 10
(def message "Let's add them all!")
(let [x (* 10 3)
      y 20
      z 100]
  (println message)
  (+ x y z))


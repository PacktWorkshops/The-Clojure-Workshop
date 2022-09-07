;; 2
(= 1 1)
(= 1 2)

;; 3
(= 1 1 1)
(= 1 1 1 -1)

;; 4
(= nil nil)
(= false nil)
(= "hello" "hello" (clojure.string/reverse "olleh"))
(= [1 2 3] [1 2 3])
(= '(1 2 3) [1 2 3])
(= 1)
(= "I will not reason and compare: my business is to create.")

;; 5
(< 1 2)
(< 1 10 100 1000)
(< 1 10 10 100)
(< 3 2 3)
(< -1 0 1)

;; 6
(<= 1 10 10 100)
(<= 1 1 1)
(<= 1 2 3)

;; 7
(> 3 2 1)
(> 3 2 2)
(>= 3 2 2)

;; 8
(not true)
(not nil)
(not (< 1 2))
(not (= 1 1))

;; Javascript code example 1:
; let x = 50;
; if (x >= 1 && x <= 100 || x % 100 == 0) {
;   console.log("Valid");
; } else {
;   console.log("Invalid");
; }
;;  Javascript code example 2:
; let x = 50;
; console.log(x >= 0 && x <= 100 || x % 100 == 0 ? "Valid" : "Invalid");

(let [x 50]
  (if (or (<= 1 x 100) (= 0 (mod x 100)))
    (println "Valid")
    (println "Invalid")))

(let [x 50]
  (println (if (or (<= 1 x 100) (= 0 (mod x 100))) "Valid" "Invalid")))

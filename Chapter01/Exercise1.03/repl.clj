;; 1
(if true "Yes" "No")

;; 2
(if false (+ 3 4) (rand))

;; 3
(doc do)

;; 4
(do (* 3 4) (/ 8 4) (+ 1 1))
(do (println "A proof that this is executed") (println "And this too"))

;; 5
(if true (do (println "Calculating a random number...") (rand)) (+ 1 2))

;; 6
(if true (do (println "Calculating a random number...") (rand)))
(if false (println "Not going to happen"))

;; 7
(when true (println "First argument") (println "Second argument") "And the last is returned")

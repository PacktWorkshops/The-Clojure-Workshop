;; 1
(get [:a :b :c] 0)
(get [:a :b :c] 2)
(get [:a :b :c] 10)

;; 2
(def fibonacci [0 1 1 2 3 5 8])
(get fibonacci 6)

;; 3
(fibonacci 6)

;; 4
(conj fibonacci 13 21)

;; 5
(let [size (count fibonacci)
       last-number (last fibonacci)
       second-to-last-number (fibonacci (- size 2))]
    (conj fibonacci (+ last-number second-to-last-number)))

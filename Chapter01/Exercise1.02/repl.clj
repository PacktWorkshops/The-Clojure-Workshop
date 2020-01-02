;; 4
 (inc 10)

;; 5
 *1

 ;; 6
(inc 10)
*1
(inc *1)
(inc *1)
(inc *2)
(inc *1)

;; 7
(/ 1 0)
*e

;; 8
(doc str)

;; 9
(str "I" "will" "be" "concatenated") (clojure.core/str "This" " works " "too")

;; 10
(doc doc)

;; 11
(doc clojure.repl)

;; 12
(find-doc "modulus")
(find-doc #"(?i)modulus")

;; 13
(mod 7 3)

;; 14
(apropos "case")
(clojure.string/upper-case "Shout, shout, let it all out")

;; 1
(if nil "Truthy" "Falsey")
(if false "Truthy" "Falsey")

;; 2
(if 0 "Truthy" "Falsey")
(if -1 "Truthy" "Falsey")
(if '() "Truthy" "Falsey")
(if [] "Truthy" "Falsey")
(if "false" "Truthy" "Falsey")
(if "" "Truthy" "Falsey")
(if "The truth might not be pure but is simple" "Truthy" "Falsey")

;; 3
(true? 1)
(if (true? 1) "Yes" "No")
(true? "true")
(true? true)
(false? nil)
(false? false)

;; 4
(nil? false)
(nil? nil)
(nil? (println "Hello"))

;; 5
(and "Hello")
(and "Hello" "Then" "Goodbye")
(and false "Hello" "Goodbye")

;; 6
(and (println "Hello") (println "Goodbye"))

;; 7
(or "Hello")
(or "Hello" "Then" "Goodbye")
(or false "Then" "Goodbye")

;; 8
(or true (println "Hello"))

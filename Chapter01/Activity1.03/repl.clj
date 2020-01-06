;; 6
(defn meditate
  "Return a transformed version of the string `s` based on the `calmness-level`"
  [s calmness-level]
  (println "Clojure Meditate v2.0")
  (if (< calmness-level 4)
    (str (clojure.string/upper-case s) ", I TELL YA!")
    (if (<= 4 calmness-level 9)
        (clojure.string/capitalize s)
        (if (= 10 calmness-level)
          (clojure.string/reverse s)))))

;; 7
(meditate "what we do now echoes in eternity" 1)
(meditate "what we do now echoes in eternity" 6)
(meditate "what we do now echoes in eternity" 10)
(meditate "what we do now echoes in eternity" 50)

;; 9
; (doc cond)

(defn meditate
  "Return a transformed version of the string `s` based on the `calmness-level`"
  [s calmness-level]
  (println "Clojure Meditate v2.0")
  (cond
    (< calmness-level 4)  (str (clojure.string/upper-case s) ", I TELL YA!")
    (<= 4 calmness-level 9) (clojure.string/capitalize s)
    (= 10 calmness-level) (clojure.string/reverse s))) 

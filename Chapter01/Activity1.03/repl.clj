;; 6
(defn meditate
  "Return a transformed version of the string `s` based on the `calmness-level`"
  [calmness-level s]
  (println "Clojure Meditate v2.0")
  (if (< calmness-level 5)
    (str (clojure.string/upper-case s) ", I TELL YA!")
    (if (<= 5 calmness-level 9)
        (clojure.string/capitalize s)
        (if (= 10 calmness-level)
          (clojure.string/reverse s)))))

;; 7
(meditate 1 "what we do now echoes in eternity")
(meditate 6 "what we do now echoes in eternity")
(meditate 10 "what we do now echoes in eternity")
(meditate 50 "what we do now echoes in eternity")

;; 9
; (doc cond)

(defn meditate
  "Return a transformed version of the string `s` based on the `calmness-level`"
  [calmness-level s]
  (println "Clojure Meditate v2.0")
  (cond
    (< calmness-level 5) (str (clojure.string/upper-case s) ", I TELL YA!")
    (<= 5 calmness-level 9) (clojure.string/capitalize s)
    (= 10 calmness-level) (clojure.string/reverse s)))

(ns packt-clj.chapter-7-tests.exercise-7-02)

;;; In REPL: 2. Simulate a source of potatoes
(def endless-potatoes
  (repeatedly (fn [] (+ 10 (rand-int 390)))))


;;; In REPL: 7. The complete function (print version)
(defn average-potatoes [prev arrivals]
  (lazy-seq
    (if-not arrivals
      '()
      (let [[_ n total] prev
            current [(first arrivals)
                     (inc (or n 0))
                     (+ (first arrivals) (or total 0))]]
        (cons current
              (average-potatoes
                current
                (next arrivals)))))))

;;; In REPL: 7. The complete function (video version)
(defn average-potatoes [prev arrivals]
  (lazy-seq
   (when (seq arrivals)
     (let [[_ n total] prev
           current [(first arrivals)
                    (inc (or n 0))
                    (+ (first arrivals) (or total 0))]]
       (cons current
             (average-potatoes
              current
              (rest arrivals)))))))

(take 3 (average-potatoes '() endless-potatoes))
;; => ([76 1 76] [90 2 166] [67 3 233])

(last (take 500000 (average-potatoes '() endless-potatoes)))
;; => [300 500000 102349308]



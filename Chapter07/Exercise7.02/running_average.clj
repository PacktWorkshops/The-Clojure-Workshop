;;; In REPL: 2. Simulate a source of potatoes
(def endless-potatoes (repeatedly (fn [] (+ 10 (rand-int 390)))))

;;; In REPL: 3. Test
(take 5 endless-potatoes)
(take 10 endless-potatoes)


;;; In REPL: 7. The complete function
(defn average-potatoes [so-far arrivals]
  (lazy-seq
    (if-not arrivals
      so-far
      (let [[_ n total] (first so-far)]
        (average-potatoes
          (cons
            [(first arrivals) (inc (or n 0)) (+ (first arrivals) (or total 0))]
            so-far)
          (next arrivals))))))

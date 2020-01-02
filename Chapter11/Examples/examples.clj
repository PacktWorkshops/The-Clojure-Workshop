;;; In REPL: minimal macros
(defmacro minimal-macro []
  '(println "I'm trapped inside a macro!"))

(defn minimal-function []
  (println "I'm trapped inside a function!"))

(macroexpand '(minimal-function))
;;; returns: (minimal-function)

(macroexpand '(minimal-macro))
;;; returns:  (println "I'm trapped inside a macro!")

(defmacro mistaken-macro []
  (println "I'm trapped... somewhere!"))

(mistaken-macro)
;;; returns: nil
;;; prints: I'm trapped... somewhere!

(macroexpand '(mistaken-macro))
;;; returns: nil
;;; prints: I'm trapped... somewhere!

;;; In REPL: minimal, but multiple times
(defmacro multi-minimal [n-times]
  (cons 'do (repeat n-times '(println "Macro"))))

(multi-minimal 3)
;;; returns: nil
;;; prints:
;;; Macro
;;; Macro
;;; Macro

(macroexpand '(multi-minimal 3))
;;; returns:
;;; (do
;;;  (println "Macro")
;;;  (println "Macro")
;;;  (println "Macro"))

;;; In REPL: a function to help the macro
(defn multi-min [n-times]
  (cons 'do (repeat n-times '(println "Macro"))))

(defmacro multi-minimal-two [n-times]
  (multi-min n-times))

(multi-minimal-two 3)
;;; returns: nil
;;; prints:
;;; Macro
;;; Macro
;;; Macro

;;; In REPL: run time parameters
;;; Warning: compiles but causes exception when used
(defmacro parameterized-multi-minimal [n-times s]
        (cons 'do (repeat n-times '(println s))))

;;; This version works
(defmacro parameterized-multi-minimal [n-times s]
  (concat (list 'let ['string-to-print s])
          (repeat n-times '(println string-to-print))))

(parameterized-multi-minimal 3 "My own text.")
;;; returns: nil
;;; prints:
;;; My own text.
;;; My own text.
;;; My own text.

;;; In REPL: syntax quoting
(defmacro parameterized-with-syntax [n-times s]
  `(do ~@(repeat n-times `(println ~s))))

(macroexpand '(parameterized-with-syntax 3 "Syntax quoting!"))
;;; returns:
;;; (do
;;;  (clojure.core/println "Syntax quoting!")
;;;  (clojure.core/println "Syntax quoting!")
;;;  (clojure.core/println "Syntax quoting!"))


;;; In REPL: macro hygiene
(defmacro let-number [[binding n] body]
  `(let [~(symbol (str binding "-as-string"))  (str ~n)
         ~(symbol (str binding "-as-int")) (int ~n)
         ~(symbol (str binding "-as-double")) (double ~n)]
     ~body))

(let-number [my-int 5]
  (type my-int-as-string))

(let [my-int-as-int 1000]
  (let-number [my-int (/ my-int-as-int 2)]
    (str "The result is: " my-int-as-double)))


;;; In REPL: auto gensyms
(defmacro let-number [[binding n] body]
  `(let [result# ~n
         ~(symbol (str binding "-as-string"))  (str result#)
         ~(symbol (str binding "-as-int")) (int result#)
         ~(symbol (str binding "-as-double")) (double result#)]
     ~body))

(let [my-int-as-int 1000.0]
  (let-number [my-int (/ my-int-as-int 2)]
    (str "The result is: " my-int-as-double)))

;;; In REPL: manual gensyms

(comment
  ;; Doesn't work because of multiple syntax-quoting environments
  (defmacro fn-context [v & symbol-fn-pairs]
    `(let [v# ~v]
       ~@(map (fn [[sym f]]
                `(defn ~sym [x#]
                   (f v# x#))) (partition 2 symbol-fn-pairs)))))

(defmacro fn-context [v & symbol-fn-pairs]
  (let [common-val-gensym (gensym "common-val-")]
    `(let [~common-val-gensym ~v]
       ~@(map (fn [[sym f]]
                `(defn ~sym [x#]
                   (~f ~common-val-gensym x#))) (partition 2 symbol-fn-pairs)))))


(let [x 100]
  (def adder (partial + x))
  (def subtractor (partial - x))
  (def multiplier (partial * x)))
















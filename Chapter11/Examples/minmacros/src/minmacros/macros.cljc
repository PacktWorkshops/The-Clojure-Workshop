(ns minmacros.macros)
(defmacro minimal-macro []
  '(println "I'm trapped inside a Clojurescript macro!"))


(defmacro runtime-includes [function-name character]
  `(defn ~(symbol function-name) []
     (if (.includes "Clojurescript macros" ~character)
       "Found it!"
       "Not here..."))) 

(defmacro js-macro [symbol-name]
  `(def ~(symbol symbol-name)
     ~(if (.includes symbol-name "b")
       "Hello"
       "Goodbye")))

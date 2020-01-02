(ns minmacros.core
  (:require-macros [minmacros.macros :as mm]))

(println "Hello from clojurescript")
(mm/minimal-macro)



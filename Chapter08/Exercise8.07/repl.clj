(require 'clojure.pprint)

(clojure.pprint/print-table [{:text "Clojure"}{:text "is"}{:text "fun"}])

(require '[clojure.pprint :as pprint])

(pprint/print-table [{:text "Clojure"}{:text "is"}{:text "fun"}])

(use 'clojure.pprint)

(print-table [{:text "Clojure"}{:text "is"}{:text "fun"}])
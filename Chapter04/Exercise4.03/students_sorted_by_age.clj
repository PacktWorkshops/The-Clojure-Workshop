(def students [{:name "Eliza" :year 1994}
               {:name "Salma" :year 1995}
               {:name "Jodie" :year 1997}
               {:name "Kaitlyn" :year 2000}
               {:name "Alice" :year 2001}
               {:name "Pippa" :year 2002}
               {:name "Fleur" :year 2002}])

;;; In REPL
(take-while #(< (:year %) 2000) students)

;;; In REPL
(drop-while #(< (:year %) 2000) students)

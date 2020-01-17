;;; In REPL
(odd? 5)
(odd? 6)
(filter odd? [1 2 3 4 5])
(remove odd? [1 2 3 4 5])
(filter (constantly true) [1 2 3 4 5])
(filter (constantly false) [1 2 3 4 5])

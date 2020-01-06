;; 1
(def supported-currencies #{"Dollar" "Japanese yen" "Euro" "Inidan rupee" "British pound"})

;; 2
(get supported-currencies "Dollar")
(get supported-currencies "Swiss franc")

;; 3
(contains? supported-currencies "Dollar")
(contains? supported-currencies "Swiss franc")

;; 4
(supported-currencies "Swiss franc")
("Dollar" supported-currencies)


;; 5
(conj supported-currencies "Monopoly Money")

;; 6
(conj supported-currencies "Monopoly Money" "Gold dragon" "Gil")

;; 7
(disj supported-currencies "Dollar" "British pound")

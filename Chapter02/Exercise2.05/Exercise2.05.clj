(def my-todo (list "Feed the cat" "Clean the bathroom" "Save the world"))
(cons "Go to work" my-todo)

(conj my-todo "Go to work")

(conj my-todo "Go to work" "Wash my socks")

(first my-todo)

(rest my-todo)
(nth my-todo 2)
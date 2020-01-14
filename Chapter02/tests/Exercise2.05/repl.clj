(require '[clojure.test :as test :refer [is are deftest run-tests]])

(def my-todo (list  "Feed the cat" "Clean the bathroom" "Save the world"))

(deftest exercise-205-test
  (is (= '("Go to work" "Feed the cat" "Clean the bathroom" "Save the world") (cons "Go to work" my-todo)))
  (is (= '("Go to work" "Feed the cat" "Clean the bathroom" "Save the world") (conj my-todo "Go to work")))
  (is (= '("Wash my socks" "Go to work" "Feed the cat" "Clean the bathroom" "Save the world") (conj my-todo "Go to work" "Wash my socks")))
  (is (= "Feed the cat" (first my-todo)))
  (is (= '("Clean the bathroom" "Save the world") (rest my-todo)))
  (is (= "Save the world" (nth my-todo 2))))

(run-tests)

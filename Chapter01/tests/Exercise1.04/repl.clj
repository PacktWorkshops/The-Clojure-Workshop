(require '[clojure.test :as test :refer [is are deftest run-tests]])

(def x 20)
(def message "Let's add them all!")

(deftest exercise104-test
  (is (= 21 (inc x)))
  (is (= 30 (let [y 3] (println y) (* 10 y))))
  (is (= "x is 10 and y is 20" (let [x 10 y 20]  (str "x is " x " and y is " y))))
  (is (= 150 (let [x (* 10 3)
                   y 20
                   z 100]
               (println message)
               (+ x y z)))))

(run-tests)

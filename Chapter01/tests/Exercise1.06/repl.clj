(require '[clojure.test :as test :refer [is are deftest run-tests]])

(deftest exercise106-test
  (is (= "Falsey" (if nil "Truthy" "Falsey")))
  (is (= "Falsey" (if false "Truthy" "Falsey")))

  (is (= "Truthy" (if 0 "Truthy" "Falsey")))
  (is (= "Truthy" (if -1 "Truthy" "Falsey")))
  (is (= "Truthy" (if '() "Truthy" "Falsey")))
  (is (= "Truthy" (if [] "Truthy" "Falsey")))
  (is (= "Truthy" (if "false" "Truthy" "Falsey")))
  (is (= "Truthy" (if "" "Truthy" "Falsey")))
  (is (= "Truthy" (if "The truth might not be pure but is simple" "Truthy" "Falsey")))

  (is (= false (true? 1)))
  (is (= "No" (if (true? 1) "Yes" "No")))
  (is (= false (true? "true")))
  (is (= true (true? true)))
  (is (= false (false? nil)))
  (is (= true (false? false)))

  (is (= false (nil? false)))
  (is (= true (nil? nil)))
  (is (= true (nil? (println "Hello"))))

  (is (= "Hello" (and "Hello")))
  (is (= "Goodbye" (and "Hello" "Then" "Goodbye")))
  (is (= false (and false "Hello" "Goodbye")))

  (is (= nil (and (println "Hello") (println "Goodbye"))))

  (is (= "Hello" (or "Hello")))
  (is (= "Hello" (or "Hello" "Then" "Goodbye")))
  (is (= "Then" (or false "Then" "Goodbye")))

  (is (= true (or true (println "Hello")))))


(run-tests)

(clojure.core/refer 'clojure.test :only '(are deftest is run-tests testing))

(def three-numbers-array (java.util.ArrayList. [0 1 2]))

(defn array-list-getter [array index]
      (.get array index))

(deftest array-list-getter-test
         (testing "Getting valid elements"
                  (are [expected actual] (= expected actual)
                       0 (array-list-getter three-numbers-array 0)
                       2 (array-list-getter three-numbers-array 2)))
         (testing "Invalid index"
                  (is (thrown? IndexOutOfBoundsException (array-list-getter three-numbers-array 5)))))


(defn array-list-getter-with-catch [array index]
      (try
        (.get array index)
        (catch IndexOutOfBoundsException ex
          (str "No element at index " index))
        (finally (println "Login usage of array-list-getter"))))

(deftest array-list-getter-with-catch-test
         (testing "Getting valid elements"
                  (are [expected actual] (= expected actual)
                       0 (array-list-getter-with-catch three-numbers-array 0)
                       2 (array-list-getter-with-catch three-numbers-array 2)))
         (testing "Invalid index"
                  (is (= "No element at index 5" (array-list-getter-with-catch three-numbers-array 5)))))

(run-tests)
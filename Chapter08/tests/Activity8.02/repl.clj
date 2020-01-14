(clojure.core/refer 'clojure.test :only '(are deftest is run-tests))

(defn parse-function [args]
      (apply + (map #(Integer/parseInt %) args)))

(deftest parse-and-add-args-test
         (are [actual expected] (= expected actual)
              3 (parse-function ["1" "2"])
              5 (parse-function ["1" "2" "2"])))

(run-tests)
(clojure.core/refer 'clojure.test :only '(are deftest is run-tests))

(clojure.core/refer 'clojure.string :only '(join replace))

(defn replace-function [& args]
      (-> (join " " args)
          (replace "melon" "banana")
          (replace "apple" "orange")))

(deftest replace-test
         (are [expected actual] (= expected actual)
              "banana carrot" (replace-function "melon" "carrot")
              "grapes orange" (replace-function "grapes" "apple")
              "carrot mango" (replace-function "carrot" "mango")))

(run-tests)
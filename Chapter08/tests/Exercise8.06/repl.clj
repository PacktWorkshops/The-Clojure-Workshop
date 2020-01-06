(in-ns 'garden)

(def vegetables ["cucumber" "carrot"])

(def fruits ["orange" "apple" "melon"])

(in-ns 'shops)

(clojure.core/refer 'garden :rename '{fruits owoce})

(clojure.core/refer 'clojure.test :only '(are deftest is run-tests))

(clojure.core/refer 'clojure.core)

(deftest vegetables-test
         (is (= vegetables
                ["cucumber" "carrot"])))

(deftest owoce-test
         (is (= owoce
                ["orange" "apple" "melon"])))

(use '[clojure.string :only [split]])

(deftest split-test
         (are [expected actual] (= expected actual)
              ["Clojure" "workshop"] (split "Clojure workshop" #" ")
              ["fruits" "garden"] (split "fruits garden" #" ")))

(use '[clojure.edn :rename {read-string string-read}])

(deftest class-test
         (is (= java.util.Date
                (class (string-read "#inst \"1989-02-06T13:20:50.52Z\"")))))

(run-tests)
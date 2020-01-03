(clojure.core/refer 'clojure.test :only '(deftest is run-tests))

(use '[clojure.string :rename {replace str-replace, reverse str-reverse}])

(def users #{"mr_paul smith" "dr_john blake" "miss_katie hudson"})

(deftest string-replace-test
         (is (= (map #(str-replace % #"_" " ") users)
                ["mr paul smith" "miss katie hudson" "dr john blake"])))

(deftest string-capitalize-test
         (is (= (map #(capitalize %) users)
                ["Mr_paul smith" "Miss_katie hudson" "Dr_john blake"])))

(def updated-users (into #{}
                         (map #(join " "
                                     (map (fn [sub-str] (capitalize sub-str))
                                          (split (str-replace % #"_" " ") #" ")))
                              users)))

(deftest update-users-test
         (is (= updated-users
                #{"Mr Paul Smith" "Dr John Blake" "Miss Katie Hudson"})))

(def admins #{"Mr Paul Smith" "Miss Katie Hudson" "Dr Mike Rose" "Mrs Tracy Ford"})

(use '[clojure.set :exclude (join)])

(deftest users-and-admins-subset-test
         (is (false? (subset? users admins))))

(run-tests)
(use '[clojure.string :rename {replace str-replace, reverse str-reverse}])

(def users #{"mr_paul smith" "dr_john blake" "miss_katie hudson"})

(map #(str-replace % #"_" " ") users)

(map #(capitalize %) users)

(def updated-users (into #{}
                         (map #(join " "
                                     (map (fn [sub-str] (capitalize sub-str))
                                          (split (str-replace % #"_" " ") #" ")))
                              users)))

(use '[clojure.set :exclude (join)])

(def admins #{"Mr Paul Smith" "Miss Katie Hudson" "Dr Mike Rose" "Mrs Tracy Ford"})

(subset?  users admins)

(use '[clojure.pprint :only (print-table)])

(print-table (map #(hash-map :user-name %) updated-users))
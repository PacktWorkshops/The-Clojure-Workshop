(in-ns 'garden)

(def vegetables ["cucumber" "carrot"])

(def fruits ["orange" "apple" "melon"])

(in-ns 'shops)

(clojure.core/refer 'garden :rename '{fruits owoce})

vegetables

fruits

owoce


(use '[clojure.string :only [split]])

(split "Clojure workshop" #" ")

(use '[clojure.edn :rename {read-string string-read}])

(class (string-read "#inst \"1989-02-06T13:20:50.52Z\""))
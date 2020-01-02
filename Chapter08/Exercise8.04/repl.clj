(in-ns 'garden)

(def vegetables ["cucumber" "carrot"])

(def fruits ["orange" "apple" "melon"])

(in-ns 'shop)

(clojure.core/refer 'garden :only '(vegetables))

fruits

garden/fruits
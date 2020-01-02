(in-ns 'garden)

(def vegetables ["cucumber" "carrot"])

(def fruits ["orange" "apple" "melon"])

(in-ns 'market)

(clojure.core/refer 'garden :exclude '(vegetables))

fruits

vegetables

garden/vegetables
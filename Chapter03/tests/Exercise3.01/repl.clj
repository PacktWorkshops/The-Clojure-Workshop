(require '[clojure.test :as test :refer [is are deftest run-tests]])

(def booking [1425, "Bob Smith", "Allergic to unsalted peanuts only", [[48.9615, 2.4372], [37.742, -25.6976]], [[37.742, -25.6976], [48.9615, 2.4372]]])

; (deftest exercise301-test
;   (is (= "1425 Bob Smith [[48.9615 2.4372] [37.742 -25.6976]] [[37.742 -25.6976] [48.9615 2.4372]] nil\n"
;          (with-out-str (let [[id customer-name sensitive-info flight1 flight2 flight3] booking] (println id customer-name flight1 flight2 flight3))))))

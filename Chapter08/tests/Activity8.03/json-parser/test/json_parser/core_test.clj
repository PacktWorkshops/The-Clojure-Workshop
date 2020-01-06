(ns json-parser.core-test
  (:require [expectations :refer [expect]]
            [json-parser.core :refer :all]))

(expect (generate-json-from-hash {:name "John" :occupation "programmer"})
        "{\"name\":\"John\",\"occupation\":\"programmer\"}")

(expect (generate-hash-from-json "{\"name\":\"Mike\",\"occupation\":\"carpenter\"}")
        {"name" "Mike", "occupation" "carpenter"})

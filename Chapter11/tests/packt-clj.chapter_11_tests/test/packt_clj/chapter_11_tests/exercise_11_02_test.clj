(ns packt-clj.chapter-11-tests.exercise-11-02-test
  (:require [packt-clj.chapter-11-tests.exercise-11-02 :as exo]
            [clojure.test :as t :refer [deftest is testing]]))


(deftest tag-construction
  (is (= "class=\"dismissed\"" (exo/attributes {:class "dismissed"})))
  ;; order of attributes is not guaranteed so we use a set
  (is (#{"class=\"dismissed\" id=\"now\""
         "id=\"now\" class=\"dismissed\""} (exo/attributes {:class "dismissed" :id "now"})))
  (is (= "<tag/>" (exo/->closed-tag "tag" nil)))
  (is (= "<tag class=\"dismissed\"/>" (exo/->closed-tag "tag" {:class "dismissed"})))
  (is (= "<tag>" (exo/->opening-tag "tag" nil)))
  (is (= "<tag class=\"dismissed\">" (exo/->opening-tag "tag" {:class "dismissed"})))
  (is (= "</tag>" (exo/->end-tag "tag"))))


(deftest automatic-function-definitions
  (exo/define-html-tags "h1" "strong")
  (is h1)
  (is strong)
  (is (= "<h1 class=\"top\">The <strong>top</strong></h1>"
         (h1 {:class "top"} "The " (strong "top")))))

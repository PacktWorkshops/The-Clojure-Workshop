(ns packt-clj.chapter-11-tests.exercise-11-03-test
  (:require [packt-clj.chapter-11-tests.exercise-11-03 :as exo]
            [clojure.test :as t :refer [deftest is testing]]))


(exo/define-html-tags "li")

(deftest subtag-fn
  (is (= "<ul class=\"my-class\"><li>Item 1</li><li>Item 2</li></ul>"
         ((exo/subtag-fn "ul" li) {:class "my-class"} ["Item 1" "Item 2"])))
  (is (=  "<ul><li>Item 1</li><li>Item 2</li></ul>"
          ((exo/subtag-fn "ul" li) ["Item 1" "Item 2"]))))


(deftest define-html-list-tags
  (exo/define-html-list-tags ["ul" "li"] ["ol" "li"])
  (is (= "<ol><li>Item 1</li><li>Item 2</li></ol>"
         (ol->li ["Item 1" "Item 2"])))
  (is (= "<ol class=\"my-class\"><li>Item 1</li><li>Item 2</li></ol>"
         (ol->li {:class "my-class"} ["Item 1" "Item 2"]))))

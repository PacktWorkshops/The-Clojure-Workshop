(ns packt-clj.chapter-6-tests.activity-6-01-test
  (:require [packt-clj.chapter-6-tests.activity-6-01 :as act]
            [clojure.test :as t :refer [deftest is testing]]))

(deftest making-tags
  (testing "simple tags"
    (is (= "<tag>" (act/keyword->opening-tag :tag)) "opening tag without attributes")
    (is (= "<tag attr=\"value\">"
           (act/keyword-attributes->opening-tag :tag {:attr "value"}))
        "opening tag with one attribute")
    (is (= "<tag attr=\"value\" another-attribute=\"more\">"
           (act/keyword-attributes->opening-tag :tag {:attr "value" :another-attribute "more"}))
        "opening tag with one attribute")
    (is (= "</tag>" (act/keyword->closing-tag :tag)))))

(deftest my-hiccup
  (testing "Simple text"
    (is (=  "text" (act/my-hiccup "text")))
    (is (= "<img src=\"/picture.png\">" (act/my-hiccup [:img {:src "/picture.png"}])))
    (is (= "<br>" (act/my-hiccup [:br])))
    (is (= "<p class=\"para\">Some <strong>nested</strong> text!</p>"
           (act/my-hiccup [:p {:class "para"} "Some " [:strong "nested"] " text!"])))))


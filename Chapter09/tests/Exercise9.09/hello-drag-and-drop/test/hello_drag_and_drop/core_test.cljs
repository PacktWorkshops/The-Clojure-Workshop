(ns hello-drag-and-drop.core-test
    (:require
     [cljs.test :refer-macros [deftest is testing]]
     [hello-drag-and-drop.core :refer [is-element-dropped? start-dragging]]))

(deftest is-element-dropped-test
         (testing "Initial value"
                  (is (false? @is-element-dropped?)))
         (testing "After dragging an element"
                  (is (false? (start-dragging {} {})))))
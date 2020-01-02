(ns test-app.core-test
    (:require
     [cljs.test :refer-macros [are deftest is testing use-fixtures]]
     [test-app.core :refer [handle-click multiply]]))

(use-fixtures :each {:before (fn [] (def app-state (atom {:counter 0})))
                     :after (fn [] (reset! app-state nil))})

(deftest handle-click-test-multiple
         (testing "Handle multiple clicks"
                  (are [result] (= result (handle-click app-state))
                       {:counter 1}
                       {:counter 2}
                       {:counter 3})))

(deftest handle-click-test-one
         (testing "Handle one click"
                  (is (= {:counter 1} (handle-click app-state)))))

(deftest multiply-test
  (is (= (* 1 2) (multiply 1 2))))

(deftest multiply-test-2
  (is (= (* 75 10) (multiply 10 75))))

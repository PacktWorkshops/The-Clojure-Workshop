(ns hello-clojurescript.core-test
    (:require
     [cljs.test :refer-macros [deftest is testing]]
     [hello-clojurescript.core :refer [app-state increment-likes]]))

(deftest increment-likes-test
         (testing "Counter initial value"
                  (is (= 0 (:counter @app-state))))
         (testing "Counter value after incrementing likes once"
                  (is (= 1 (:counter (increment-likes)))))
         (testing "Counter value after incrementing likes twice"
                  (is (= 2 (:counter (increment-likes))))))

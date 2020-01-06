(ns js-interop.core
  (:require [clojure.browser.repl :as repl]
            [cljs.test :refer-macros [are deftest is testing run-tests]]))

(enable-console-print!)


(def rivers-map-cljs {:country {:river "Donau"}})

(deftest clojure-map-test
         (is (= "Donau" (:river (:country rivers-map-cljs)))))

(def rivers-map-js (js-obj "country" {"river" "Donau"}))

(deftest js-object-test
         (are [expected actual] (= expected actual)
              cljs.core/PersistentArrayMap (type (.-country rivers-map-js))
              {"river" "Donau"} (.-country rivers-map-js)
              nil (.-river (.-country rivers-map-js))))

(def rivers-map-js-converted (clj->js {"country" {"river" "Donau"}}))

rivers-map-js-converted

(deftest cljs->js-test
         (is (= "Donau" (.-river (.-country rivers-map-js-converted)))))

(deftest js->cljs-test
         (are [expected actual] (= expected actual)
              {"river" "Donau"} (js->clj #js {:river "Donau"})
              {"country" {"river" "Donau"}} (js->clj #js {:country #js {:river "Donau"}})))

(run-tests)
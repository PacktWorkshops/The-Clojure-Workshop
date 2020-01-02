(ns js-interop.core
  (:require [clojure.browser.repl :as repl]))


(def rivers-map-cljs {:country {:river "Donau"}})

(:river (:country rivers-map-cljs))

(js-obj "Austria" "Donau")

(def rivers-map-js (js-obj "country" {"river" "Donau"}))

rivers-map-js

(.-country rivers-map-js)

(.-river (.-country rivers-map-js))


(def rivers-map-js-converted (clj->js {"country" {"river" "Donau"}}))

rivers-map-js-converted

(.-country rivers-map-js-converted)

(.-river (.-country rivers-map-js-converted))

(js->clj #js {:river "Donau"})

(js->clj #js {:country #js {:river "Donau"}})
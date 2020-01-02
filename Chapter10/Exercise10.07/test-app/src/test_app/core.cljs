(ns ^:figwheel-hooks test-app.core
  (:require
   [goog.dom :as gdom]
   [rum.core :as rum]))

(println "This text is printed from src/test_app/core.cljs. Go ahead and edit it and see reloading in action.")

(defn multiply [a b] (* a b))


;; define your app data so that it doesn't get over-written on reload
(defonce state (atom {:counter 0}))

(defn get-app-element []
  (gdom/getElement "app"))

(rum/defc hello-world []
  [:div
   [:h3 "Edit this in src/test_app/core.cljs and watch it change!"]])

(defn mount [el]
  (rum/mount (hello-world) el))

(defn mount-app-element []
  (when-let [el (get-app-element)]
    (mount el)))

;; conditionally start your application based on the presence of an "app" element
;; this is particularly helpful for testing this ns without launching the app
(mount-app-element)

;; specify reload hook with ^;after-load metadata
(defn ^:after-load on-reload []
  (mount-app-element))
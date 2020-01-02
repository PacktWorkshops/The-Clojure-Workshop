(ns ^:figwheel-hooks hello-clojurescript.core
  (:require
   [goog.dom :as gdom]
   [rum.core :as rum]))

(println "This text is printed from src/hello_clojurescript/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom {:text "Hello world!" :counter 0}))

(defn get-app-element []
  (gdom/getElement "app"))

;(rum/defc hello-world []
;  [:div
;   [:h1 (:text (deref app-state))]
;   [:h3 "Edit this in src/hello_clojurescript/core.cljs and watch it change!"]])

(defn increment-likes []
      (swap! app-state update-in [:counter] inc))

(rum/defc band [name likes]
          [:div {:class "band"
                 :on-click #(increment-likes)}
           (str name " is liked " likes " times")])

(rum/defc hello-world < rum/reactive []
          [:div {}
           (band "Metallica" (:counter (rum/react app-state)))])

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
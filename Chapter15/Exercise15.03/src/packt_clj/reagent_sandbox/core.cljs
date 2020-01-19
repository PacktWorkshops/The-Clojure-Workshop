(ns packt-clj.reagent-sandbox.core
    (:require [reagent.core :as r]))

(enable-console-print!)

(defonce app-state (atom {:text "Hello world!"
                          :button-on? true}))

(defn button []
  (let [text (if (get-in @app-state [:button-on?]) "ON" "OFF")]
    [:button
     {:on-click #(swap! app-state update-in [:button-on?] not)}
     text]))

(defn hello-world []
  [:div
   [:h1 (:text @app-state)]
   [button]
   [:div [image "https://picsum.photos/id/0/5616/3744"]]
   [:h3 "Edit this and watch it change!"]]

(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )

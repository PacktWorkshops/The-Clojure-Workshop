(ns packt-clj.reagent-sandbox.core
    (:require [reagent.core :as reagent]))

(enable-console-print!)

(defonce app-state (atom {:text "Hello world!"}))


(defn image [url]
  [:img {:src url
         :style {:width "500px" 
                 :border "solid gray 3px"
                 :border-radius "10px"}}])

(defn hello-world []
  [:div
   [:h1 (:text @app-state)]
   [:div [image "https://picsum.photos/id/0/5616/3744"]]
   [:h3 "Edit this and watch it change!"]])

(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )

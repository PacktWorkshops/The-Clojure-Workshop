(ns packt-clj.reagent-sandbox.core
    (:require [reagent.core :as reagent]))

(enable-console-print!)

(defonce app-state (atom {:text "Hello world!"}))

(defn image-with-width [url width]
  [:img {:src url
         :style {:width width
                 :border "solid gray 3px"
                 :border-radius "10px"}}])

(def my-images
  ["https://picsum.photos/id/0/5616/3744"
   "https://picsum.photos/id/1/5616/3744"
   "https://picsum.photos/id/10/2500/1667"
   "https://picsum.photos/id/100/2500/1656"
   "https://picsum.photos/id/1000/5626/3635"
   "https://picsum.photos/id/1001/5616/3744"
   "https://picsum.photos/id/1002/4312/2868"
   "https://picsum.photos/id/1003/1181/1772"
   "https://picsum.photos/id/1004/5616/3744"
   "https://picsum.photos/id/1005/5760/3840"])

(defn image-grid [images]
  (into [:div] (map (fn [image-data] [:div {:style {:float "left"
                                                   :margin-left "20px"}}
                                     [image-with-width image-data "50px"]]) images)))

(defn hello-world []
  [:div
   [:h1 (:text @app-state)]
   [image-grid my-images]])

(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )

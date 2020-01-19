(ns packt-clj.reagent-sandbox.core
    (:require [reagent.core :as reagent]))

(enable-console-print!)

(defonce app-state (atom {:text "Hello world!"
                          :button-on? true}))

(defn hello-world []
  [:div
   [:h1 (:text @app-state)]
   [:h3 "Edit this and watch it change!"]])

(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))



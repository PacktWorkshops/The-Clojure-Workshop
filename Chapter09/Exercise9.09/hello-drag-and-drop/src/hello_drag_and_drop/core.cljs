(ns ^:figwheel-hooks hello-drag-and-drop.core
  (:require [goog.dom :as gdom]
            [jayq.core :as jayq :refer [$]]
            [rum.core :as rum]
            [sablono.util]))

;; define your app data so that it doesn't get over-written on reload
(defonce is-element-dropped? (atom false))

(defn attrs [a]
      (clj->js (sablono.util/html-to-dom-attrs a)))

(defn get-app-element []
  (gdom/getElement "app"))

(rum/defc tile [text number]
          [:div {:class "tile" :id number} text])

(rum/defc tiles []
          [:.tiles {}
           (tile "first" 1)
           (tile "second" 2)])

(rum/defc dropped-message < rum/reactive []
          [:div {}
           (str "Was element dropped? " (rum/react is-element-dropped?))])

(rum/defc card [number]
          [:.card {:data-number number
                   :id number}])

(rum/defc cards []
          [:.cards {}
           (card 1)
           (card 2)])

(rum/defc content []
          [:div {}
           (tiles)
           (cards)
           (dropped-message)])

(defn mount [el]
  (rum/mount (content) el))

(defn mount-app-element []
  (when-let [el (get-app-element)]
    (mount el)))

(defn make-draggable []
      (.draggable ($ ".card") (attrs {:revert true :cursor "move"})))

(defn handle-drop [event ui]
      (let [draggable-id (jayq/data (.-draggable ui) "number")]
           (println "Dropping element with id" draggable-id)
           (reset! is-element-dropped? true)
           (.draggable (.-draggable ui) "disable")
           (.draggable (.-draggable ui) (attrs {:revert false}))
           (.droppable ($ (str "#" (.-id (.-target event)))) "disable")
           (.position (.-draggable ui)
                      (attrs {:of ($ (str "#" (.-id (.-target event)))) :my "left top" :at "left top"}))))

(defn start-dragging [event ui]
      (reset! is-element-dropped? false))

(defn make-droppable []
      (.droppable ($ (str ".tile"))
                  (attrs {:hoverClass "hovered-tile" :drop handle-drop :activate start-dragging})))

;; conditionally start your application based on the presence of an "app" element
;; this is particularly helpful for testing this ns without launching the app
(mount-app-element)
(make-draggable)
(make-droppable)

;; specify reload hook with ^;after-load metadata
(defn ^:after-load on-reload []
  (mount-app-element)
  (make-draggable)
  (make-droppable))
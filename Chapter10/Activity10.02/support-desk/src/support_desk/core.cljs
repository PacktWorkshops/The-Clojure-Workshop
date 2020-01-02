(ns ^:figwheel-hooks support-desk.core
  (:require [cuerdas.core :as str]
            [goog.dom :as gdom]
            [jayq.core :as jayq :refer [$]]
            [rum.core :as rum]
            [support-desk.utils :as utils]))

(def priorities-list [{:title "IE bugs" :priority 2} {:title "404 page" :priority 1} {:title "Forgotten username" :priority 2}
                      {:title "Login token" :priority 1} {:title "Mobile version" :priority 3} {:title "Load time" :priority 5}])


;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom {:sort-counter 0
                          :items        (utils/get-sorted-priorities-list (utils/get-priorities-list priorities-list 3))}))

(rum/defc counter [number]
          [:div
           (str/format "Sorting done: %s times" (utils/get-sort-message number))])

(defn done-button-click [item]
      (swap! app-state update-in [:items] #(utils/delete-item-from-list-by-title (:title item) %)))

(rum/defc item [item]
          [:li.ui-state-default {:key (:title item)}
           (str/format "Priority %s for: %s " (:priority item) (:title item))
           [:button.delete
            {:on-click #(done-button-click item)}
            "Done"]])

(rum/defc items < rum/reactive [num]
          [:ul#sortable (vec (for [n num]
                                  (item n)))])

(rum/defc content < rum/reactive []
          [:div {}
           (items (:items (deref app-state)))
           (counter (:sort-counter (rum/react app-state)))])

(defn attrs [a]
      (clj->js (sablono.util/html-to-dom-attrs a)))

(defn make-sortable []
      (.sortable ($ (str "#sortable"))
                 (attrs {:stop (utils/handle-sort-finish app-state)})))

(defn get-app-element []
  (gdom/getElement "app"))

(defn mount [el]
  (rum/mount (content) el))

(defn mount-app-element []
  (when-let [el (get-app-element)]
    (mount el)))

;; conditionally start your application based on the presence of an "app" element
;; this is particularly helpful for testing this ns without launching the app
(mount-app-element)
(make-sortable)

;; specify reload hook with ^;after-load metadata
(defn ^:after-load on-reload []
  (mount-app-element)
  (make-sortable))
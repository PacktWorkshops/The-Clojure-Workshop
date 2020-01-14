(ns packt-clj.images.core
    (:require [reagent.core :as r]))
              
(enable-console-print!)

(defonce app-state (r/atom {:images []
                            :author-display true}))

(defn image [{:keys [download_url author]}]
  [:div
   [:img {:src download_url
          :height "130px"
          :style {:border "solid gray 3px"
                  :border-radius "10px"}}]
   (when (:author-display @app-state)
      [:div {:style {:font-size "15px"
                  :color "gray" }}
         (str "Image by ") author])])

(defn image-grid [images]
   (if (empty? images)
      [:div "Click the button to fetch images"]
      (into [:div] (map (fn [image-data] [:div {:style {:float "left"
                                                        :margin-left "20px"}}
                                               [image image-data]]) images))))

(defn author-display-button []
  (let [text (if (:author-display @app-state)
                 "Hide author"
                 "Show author")]
  [:button.btn {:on-click #(swap! app-state update-in [:author-display] not)}
           text]))

(defn fetch-images []
 (-> (js/fetch "https://picsum.photos/v2/list?limit=6")
  (.then (fn [response] (.json response)))
  (.then (fn [json] (swap! app-state assoc-in [:images] (js->clj json :keywordize-keys true))))))

(defn clear-images []
     (swap! app-state assoc-in [:images] []))

(defn fetch-or-clear-button []
  (let [handler (if (empty? (:images @app-state)) fetch-images clear-images)
        text    (if (empty? (:images @app-state)) "Fetch Images" "Clear Images")]
    [:button.btn {:on-click handler} text]))
  
(defn app []
  [:div
   [fetch-or-clear-button]
   [author-display-button]
   [image-grid (:images @app-state)]])

(r/render-component [app] (. js/document (getElementById "app")))



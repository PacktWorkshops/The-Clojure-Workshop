(ns packt-clj.tennis.core
  (:require [reagent.core :as r]))

(enable-console-print!)

(defonce app-state (r/atom {:players []
                            :current-player nil}))

(defn fetch-player [id full_name]
  (-> (js/fetch (str "http://localhost:8080/players/" id "/elo"))
      (.then (fn [response] (.json response)))
      (.then (fn [json] (swap! app-state assoc-in [:current-player] (assoc (js->clj json :keywordize-keys true)
                                                                          :full_name full_name))))))

(defn player-alone [{:keys [rating full_name]}]
  [:div
   (str full_name " has a rating of: " rating)])

(defn player-list-button []
  [:button.btn {:on-click #(swap! app-state assoc-in [:current-player] nil)} "Display all players"])

(defn player [{:keys [id full_name]}]
  [:div [:span
         [:a
          {:href "#"
           :on-click (partial fetch-player id full_name)}
          full_name]]])

(defn player-list [players]
  (if (empty? players)
    [:div "Click the button to fetch players"]
    (into [:div] (map player players))))


(defn fetch-players []
  (-> (js/fetch "http://localhost:8080/players/")
      (.then (fn [response] (.json response)))
      (.then (fn [json] (swap! app-state assoc-in [:players] (js->clj json :keywordize-keys true))))))

(defn clear-players []
  (swap! app-state assoc-in [:players] []))


(defn fetch-or-clear-button []
  (let [handler (if (empty? (:players @app-state)) fetch-players clear-players)
        text    (if (empty? (:players @app-state)) "Fetch Players" "Clear Players")]
    [:button.btn {:on-click handler} text]))
  
(defn app []
  (if (:current-player @app-state)
    [:div
     [player-list-button]
     [player-alone (:current-player @app-state)]]
    [:div
     [fetch-or-clear-button]
     [player-list (:players @app-state)]]))

(r/render-component [app] (. js/document (getElementById "app")))



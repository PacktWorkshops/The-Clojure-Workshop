(ns packt-clj.rock-scissors-paper.core
    (:require [rum.core :as rum]))

(enable-console-print!)

(println "This text is printed from src/packt-clj.rock-scissors-paper/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:computer-choice nil
                          :game-state :setup
                          :player-choice nil}))

(defn computer-choice []
  (get [:rock :scissors :paper] (rand-int 3)))

(def object-sets->messages
  {#{:rock :scissors} "Rock crushes scissors."
   #{:scissors :paper} "Scissors cut paper."
   #{:paper :rock} "Paper wraps rock."})

(defn result-messages [a b]
  (get object-sets->messages (hash-set a b)))

(def resolutions
  {:rock {:paper :computer-wins
          :scissors :player-wins}
   :scissors {:rock :computer-wins
              :paper :player-wins}
   :paper {:scissors :computer-wins
           :rock :player-wins}})


(defn resolve-game [player computer]
  (if (= player computer)
    :tie
    (get-in resolutions [player computer])))

(defn start-game []
  (swap! app-state
         (fn [state]
           (assoc state
                  :computer-choice (computer-choice)
                  :game-state :waiting))))

(defn player-choice [object]
  (fn []
    (swap! app-state
           (fn [state]
             (assoc state
                    :player-choice object
                    :game-state :complete)))))


(rum/defc result-view < rum/reactive []
  (let [player (:player-choice (rum/react app-state))
        computer (:computer-choice (rum/react app-state))
        result (resolve-game player computer)]
    [:div
     [:div "You played " [:strong (name player)]]
     [:div "The computer played " [:strong (name computer)]]
     (if (= result :tie)
       [:div "It was a tie!"]
       [:div
        [:div (result-messages player computer)]
        [:div (if (= result :player-wins) "You won!" "Oops. The computer won.")]])
     [:div [:a {:href "#start"
                :onClick start-game} "Play again?"]]]))

(rum/defc game-view < rum/reactive []
  (case (:game-state (rum/react app-state))
    :setup
    [:div "Ready to play?"
     [:div [:a {:href "#start"
                :onClick start-game} "Start"]]]
    :waiting
    [:div "Choose one"
     [:div [:a {:href "#rock"
                :onClick (player-choice :rock)} "Rock"]]
     [:div [:a {:href "#paper"
                :onClick (player-choice :paper)} "Paper"]]
     [:div [:a {:href "#scissors"
                :onClick (player-choice :scissors)} "Scissors"]]]

    :complete
    (result-view)))

(rum/defc rock-paper-scissors []
  [:div
   [:h1 "Rock, Paper, Scissors"]
   (game-view)])

(defn on-js-reload []
  (rum/mount (rock-paper-scissors)
             (. js/document (getElementById "app"))))

(on-js-reload)

(ns packt-clj.chapter-5-tests.exercise-5-03)

(defn streak-string [current-wins current-losses]
  (cond (pos? current-wins) (str "Won " current-wins)
        (pos? current-losses) (str "Lost " current-losses)
        :otherwise "First match of the year"))

(defn serena-williams-win-loss-streaks [matches]
  (:matches
   (reduce (fn [{:keys [current-wins current-losses] :as acc} {:keys [winner-name] :as match}]
             (let [this-match (assoc match :current-streak (streak-string current-wins current-losses))
                   serena-victory? (= winner-name "Williams S.")]
               (-> acc
                   (update :matches #(conj % this-match))
                   (assoc :current-wins (if serena-victory?
                                          (inc current-wins)
                                          0))
                   (assoc :current-losses (if serena-victory?
                                            0
                                            (inc current-losses))))))
           {:matches []
            :current-wins 0
            :current-losses 0}

           matches)))



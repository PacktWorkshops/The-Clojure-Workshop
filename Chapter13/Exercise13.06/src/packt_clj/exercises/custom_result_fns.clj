(ns packt-clj.exercises.custom-result-fns
  (:require
    [clojure.java.jdbc :as jdbc]
    [clojure.string :as str]))

(defn add-user-friendly-duration
  [{:keys [duration] :as row}]
  (let [quot-rem (juxt quot rem)
        [hours remainder] (quot-rem duration (* 60 60))
        [minutes seconds] (quot-rem remainder 60)]
    (assoc row :friendly-duration
               (cond-> ""
                       (pos? hours) (str hours "h ")
                       (pos? minutes) (str minutes "m ")
                       (pos? seconds) (str seconds "s")
                       :always str/trim))))

(defn all-activities-user-friendly-duration
  [db]
  (jdbc/query db ["select * from activity"]
              {:row-fn add-user-friendly-duration}))

(defn all-activities-total-duration
  [db]
  (jdbc/query db ["select * from activity"]
              {:result-set-fn (fn [result-set]
                                (reduce (fn [total-distance {:keys [distance]}]
                                          (+ total-distance distance))
                                        0
                                        result-set))}))

(defn all-activities-total-duration-alternative
  [db]
  (jdbc/query db ["select * from activity"]
              {:row-fn        :distance
               :result-set-fn #(apply + %)}))

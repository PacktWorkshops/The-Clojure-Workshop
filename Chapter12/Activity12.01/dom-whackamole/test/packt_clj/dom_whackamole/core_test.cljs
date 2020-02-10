(ns packt-clj.dom-whackamole.core-test
  (:require
   [packt-clj.dom-whackamole.core :as whack]
   [cljs.test :as t :refer [deftest is testing]]))


(defn reset-atoms! []
  (reset! whack/points 0)
  (reset! whack/millis-remaining (* whack/game-length-in-seconds 1000))
  (reset! whack/game-state :waiting)
  (reset! whack/clock-interval nil)
  (reset! whack/moles (into [] (repeat 5 {:status :waiting :remaining-millis 0})))
  (reset! whack/app-state {:points 0
                     :millis-remaining (* whack/game-length-in-seconds 1000)
                     :game-state :waiting
                     :clock-interval nil
                     :moles (into []
                                  (repeat 5 {:status :waiting
                                             :remaining-millis 0}))}))

(deftest activate-deactivate-mole
  (reset-atoms!)
  (is (zero? (count (filter #(= :live (:status %)) @whack/moles))))
  (whack/activate-mole 0)
  (is (= :live (:status (first @whack/moles))))
  (is (= 3000 (:remaining-millis (first @whack/moles))))
  (is (= 1 (count (filter #(= :live (:status %)) @whack/moles))))
  (whack/deactivate-mole 0)
  (is (= :waiting (:status (first @whack/moles)))))

(deftest mole-countdown
  (reset-atoms!)
  (whack/activate-mole 0)
  (whack/mole-countdown)
  (is (= 2900 (:remaining-millis (first @whack/moles))))
  (dotimes [n 30] (whack/mole-countdown))
  (is (zero? (:remaining-millis (first @whack/moles)))))


(deftest update-and-reset-moles
  (reset-atoms!)
  (whack/update-moles)
  (is (= 1 (count (filter #(= :live (:status %)) @whack/moles))))
  (whack/update-moles)
  (is (= 2 (count (filter #(= :live (:status %)) @whack/moles))))
  (whack/reset-moles)
  (is (zero? (count (filter #(= :live (:status %)) @whack/moles)))))

(deftest whack-a-mole
  (reset-atoms!)
  (whack/activate-mole 0)
  (is (= :live (:status (first @whack/moles))))
  (whack/whack! 0)
  (is (= :waiting (:status (first @whack/moles)))))

(deftest clock
  (reset-atoms!)
  (is (= 20000 @whack/millis-remaining))
  (whack/clock-tick)
  (is (= 19900 @whack/millis-remaining))
  (whack/activate-mole 0)
  (is (= 19900 @whack/millis-remaining))
  (is (= 3000 (:remaining-millis (first @whack/moles))))
  (whack/clock-tick)
  (is (= 2900 (:remaining-millis (first @whack/moles)))))




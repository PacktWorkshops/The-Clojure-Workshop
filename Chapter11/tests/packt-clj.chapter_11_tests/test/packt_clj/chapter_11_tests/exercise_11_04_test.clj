(ns packt-clj.chapter-11-tests.exercise-11-04-test
  (:require
   [clojure.test :as t :refer [deftest is testing]]
   [packt-clj.chapter-11-tests.exercise-11-04 :as exo]))


(deftest monitored
  (testing "normal case"
    (let [tx-result (atom nil)]
      (exo/defmonitored my-monitored #(swap! tx-result (fn [at x] x) %) [arg client-id]
        (Thread/sleep 1000)
        arg)
      (my-monitored 5 55)
      (is (map? @tx-result))
      (is (= 55 (:client-id @tx-result)))
      (is (> (:end-time @tx-result) (:start-time @tx-result)))))

  (testing "exceptions"
    (let [tx-result (atom nil)]
      (exo/defmonitored my-monitored #(swap! tx-result (fn [at x] x) %) [arg client-id]
        (Thread/sleep 1000)
        (throw (ex-info "Exception!" {}))
        arg)
      (is (thrown? Exception 
            (my-monitored 5 55)))
      (is (map? @tx-result))
      (is (= 55 (:client-id @tx-result)))
      (is (> (:end-time @tx-result) (:start-time @tx-result)))
      (is (= :error (:status @tx-result))))))


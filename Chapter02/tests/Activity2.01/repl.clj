(require '[clojure.test :as test :refer [is are deftest run-tests]])

(load-file "../../Activity2.01/repl.clj")

(deftest activity-201-test
  (is (= {:fruits {:data [], :indexes {}}} (create-table :fruits)))
  (is (= {:clients {:data [], :indexes {}}, :fruits {:data [], :indexes {}}} (create-table :clients)))
  (let [expected-db {:fruits {:data [{:name "Pear", :stock 3}], :indexes {:name {"Pear" 0}}}, :clients {:data [], :indexes {}}}]
   (is (= expected-db (insert :fruits {:name "Pear" :stock 3} :name))))
  (is (= nil (insert :fruits {:name "Pear" :stock 3} :name)))
  (let [expected-db {:fruits {:data [{:name "Pear", :stock 3} {:name "Apricot", :stock 30}], :indexes {:name {"Pear" 0, "Apricot" 1}}}, :clients {:data [], :indexes {}}}]
    (is (= expected-db (insert :fruits {:name "Apricot" :stock 30} :name))))
  (is (= [{:name "Pear", :stock 3} {:name "Apricot", :stock 30}] (select-* :fruits)))
  (is (= {:name "Apricot", :stock 30} (select-*-where :fruits :name "Apricot"))))

(run-tests)
;; 1
(def memory-db (atom {}))
(defn read-db [] @memory-db)
(defn write-db [new-db] (reset! memory-db new-db))

;; 2
(defn create-table
  [table-name]
  (let [db (read-db)]
    (write-db (assoc db table-name {:data [] :indexes {}}))))

;; 4
(defn drop-table
  [table-name]
  (let [db (read-db)]
    (write-db (dissoc db table-name))))

;; 6
(defn insert
  [table-name record id-key]
    (let [db (read-db)
          new-db (update-in db [table-name :data] conj record)
          index (- (count (get-in new-db [table-name :data])) 1)]
      (write-db
        (update-in new-db [table-name :indexes id-key] assoc (id-key record) index))))

;; 8
(defn select-*
  [table-name]
  (get-in (read-db) [table-name :data]))

;; 9
(defn select-*-where
  [table-name field field-value]
  (let [db (read-db)
        index (get-in db [table-name :indexes field field-value])
        data (get-in db [table-name :data])]
    (get data index)))

;; 10
(defn insert
  [table-name record id-key]
  (if-let [existing-record (select-*-where table-name id-key (id-key record))]
    (println (str "Record with " id-key ": " (id-key record) " already exists. Aborting"))
    (let [db (read-db)
          new-db (update-in db [table-name :data] conj record)
          index (- (count (get-in new-db [table-name :data])) 1)]
      (write-db
        (update-in new-db [table-name :indexes id-key] assoc (id-key record) index)))))

(ns packt-clj.chapter-11-tests.activity-11-01
  (:require
   [clojure.java.io :as io]
   [clojure.data.csv :as csv]
   [semantic-csv.core :as sc]))

(defn maybe-select-keys [m maybe-keys]
  (if (seq maybe-keys)
    (select-keys m maybe-keys)
    m))

(defmacro with-tennis-csv [csv casts fields & forms]
  `(with-open [reader# (io/reader ~csv)]
     (->> (csv/read-csv reader#)
          sc/mappify
          (sc/cast-with ~casts)

          ~@forms

          (map #(maybe-select-keys % ~fields))
          doall)))



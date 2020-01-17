;;; In REPL
(require '[clojure.data.csv :as csv])
(require '[clojure.java.io :as io])


(comment
  ;;; Fails with error
  (with-open [r (io/reader "match_scores_1991-2016_unindexed_csv.csv")]
    (->> (csv/read-csv r)
         (map #(nth % 7))
         (take 6))))

(with-open [r (io/reader "match_scores_1991-2016_unindexed_csv.csv")]
    (->> (csv/read-csv r)
         (map #(nth % 7))
         (take 6)
         doall))


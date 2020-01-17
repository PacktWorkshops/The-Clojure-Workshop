;;; In REPL

(require '[clojure.data.csv :as csv])
(require '[clojure.java.io :as io])
(with-open [r (io/reader "match_scores_1991-2016_unindexed_csv.csv")]
  (first (csv/read-csv r)))

(with-open [r (io/reader "match_scores_1991-2016_unindexed_csv.csv")]
  (count (csv/read-csv r)))


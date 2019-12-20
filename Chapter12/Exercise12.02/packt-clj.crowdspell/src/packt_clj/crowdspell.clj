(ns packt-clj.crowdspell
  (:require
   [clojure.tools.cli :as cli]
   [packt-clj.crowdspell.fetch :as fetch])
  (:gen-class))


(defn -main
  [& args]
  (let [parsed (cli/parse-opts
                 args
                 [["-l" "--language LANG" "Two-letter language code for search"
                   :default "en"]])]
    (println (fetch/get-best-word (get-in parsed [:options :language])
                                  (:arguments parsed)))))

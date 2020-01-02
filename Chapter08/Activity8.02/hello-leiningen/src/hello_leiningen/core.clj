(ns hello-leiningen.core)

(defn -main
  "Sum integers passed as arguments."
  [& args]
  (println (apply + (map #(Integer/parseInt %) args))))

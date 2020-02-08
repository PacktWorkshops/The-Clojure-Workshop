(ns packt-clj.crowdspell.fetch
  (:require [clj-http.client :as http]
            [clojure.data.json :as json]))

;https://en.wikipedia.org/w/api.php?action=query&list=search&srlimit=1&srsearch=clojure
(defn word-search [word language-code]
  (try
    (let [http-result (http/get (str "https://" language-code ".wikipedia.org/w/api.php")
                                {:query-params {"action" "query"
                                                "list" "search"
                                                "srlimit" 1
                                                "srsearch" word
                                                "format" "json"}
                                 :accept :json
                                 :cookie-policy :none})
          total-hits  (-> (json/read-str (:body http-result) :key-fn keyword)
                          (get-in [:query :searchinfo :totalhits]))]
      {:status :ok :total-hits total-hits :word word})
    (catch Exception e
      {:status :error})))


(defn get-best-word
  [language-code words]
  (let [results (map (fn [a] [a (future (word-search a language-code))]) words)]
    (-> 
      (reduce (fn [best-so-far [word result-future]]
                (let [{:keys [status total-hits] :as result} @result-future]
                  (if (= status :ok)
                    (if (> total-hits (:total-hits best-so-far))
                      result
                      best-so-far)
                    best-so-far)))
              {:total-hits 0}
              results)
      :word)))


(ns packt-clj.crowdspell.fetch
  (:require [clj-http.client :as http]
            [clojure.data.json :as json]))

;https://en.wikipedia.org/w/api.php?action=query&list=search&srlimit=1&srsearch=clinton
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
(comment
  (defn word-search [word language-code]
    (try
      ;; TODO: the HTTP request
      (catch Exception e
        {:status :error})))


  (defn word-search [word language-code]
    (try
      (let [http-result (http/get (str "https://" language-code ".wikipedia.org/w/api.php")
                                  {:query-params {"action" "query"
                                                  "list" "search"
                                                  "srlimit" 1
                                                  "srsearch" word
                                                  "format" "json"}
                                   :accept :json
                                   :cookie-policy :none})]
        ;; TODO: do something with the result
        {:status :ok :total-hits total-hits :word word})
      (catch Exception e
        {:status :error})))

  (-> (json/read-str (:body http-result) :key-fn keyword)
      (get-in [:query :searchinfo :totalhits])))

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


(comment
  (defn get-best-word
    [language-code words]
    (let [results (->> words
                       (map (fn [a] [a (future (word-search a language-code))]))
                       (remove #(not= (:status (deref %)) :ok)))]
      ))

  ;; TODO: decide which word is the best

  (reduce (fn [best-so-far [word result-future]]b
            (let [{:keys [status total-hits] :as result} @result-future]
              (if (= status :ok)
                (if (> total-hits (:total-hits best-so-far))
                  result
                  best-so-far)
                best-so-far)))
          {:total-hits 0}
          results))

;;in REPL
(-> (js/fetch "https://picsum.photos/v2/list?limit=3")
    (.then (fn [response] (.json response)))
    (.then (fn [json] (println  (js->clj json :keywordize-keys true)))))

(ns hello-test.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :refer [<!]]
            [cljs-http.client :as http]
            [cuerdas.core :as str]))

(defn profanity-filter [string]
      (if (str/includes? string "bad")
        (str/replace string "bad" "great")
        string))

(defn prefix-digit-remover[string]
      (if (str/starts-with? string "1")
        (str/replace-first string "1" "a")
        string))

(defn http-get [url params callback]
      (go (let [response (<! (http/get url params))]
               (callback response))))
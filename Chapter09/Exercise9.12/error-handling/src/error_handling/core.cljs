(ns error-handling.core
  (:require [clojure.browser.repl :as repl]))

(def languages {:Clojure "CLJ"
                :ClojureScript "CLJS"
                :JavaScript "JS"})

(defn language-abbreviator [language]
      (if-let [lang (get languages language)]
              lang
              (throw (js/Error. "Language not supported"))))

(language-abbreviator :JavaScript)

(language-abbreviator :Clojure)

(language-abbreviator :Ruby)

(defn get-language-of-the-week [languages]
      (let [lang-of-the-week (rand-nth languages)]
           (try
             (str "The language of the week is: " (language-abbreviator lang-of-the-week))
             (catch js/Error e
               (str lang-of-the-week " is not a supported language"))
             (finally (println lang-of-the-week "was chosen as the language of the week")))))

(get-language-of-the-week [:Clojure :JavaScript :ClojureScript])

(get-language-of-the-week [:Ruby :Kotlin :Go])

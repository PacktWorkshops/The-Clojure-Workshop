(ns error-handling.core
  (:require [cljs.test :refer-macros [are deftest is testing run-tests]]))

(def languages {:Clojure "CLJ"
                :ClojureScript "CLJS"
                :JavaScript "JS"})

(defn language-abbreviator [language]
      (if-let [lang (get languages language)]
              lang
              (throw (js/Error. "Language not supported"))))

(defn get-language-of-the-week [languages]
      (let [lang-of-the-week (rand-nth languages)]
           (try
             (str "The language of the week is: " (language-abbreviator lang-of-the-week))
             (catch js/Error e
               (str lang-of-the-week " is not a supported language"))
             (finally (println lang-of-the-week "was chosen as the language of the week")))))

(deftest language-abbreviator-test
         (testing "Valid languages"
                  (are [expected actual] (= expected actual)
                       "JS" (language-abbreviator :JavaScript)
                       "CLJ" (language-abbreviator :Clojure)))
         (testing "Invalid language"
                  (is (thrown? js/Error (language-abbreviator :Ruby)))))

(deftest get-language-of-the-week-test
         (testing "Valid languages"
                  (is (true? (boolean (re-find #"The language of the week is:" (get-language-of-the-week [:Clojure :JavaScript :ClojureScript]))))))
         (testing "Invalid languages"
                  (is (true? (boolean (re-find #"is not a supported language" (get-language-of-the-week [:Ruby :Kotlin :Go])))))))
(run-tests)
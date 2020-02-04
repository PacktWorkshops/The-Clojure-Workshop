(ns packt-clj.chapter-11-tests.exercise-11-03
  (:require [clojure.string :as string]))

;;; These functions are identical to those in Exercise 11.02 but we
;;; repeat them here so that we don't need to namespace them inside
;;; the macros.
(defn attributes [m]
  (clojure.string/join " "
                       (map (fn [[k v]]
                              (if (string? v)
                                (str (name k) "=\"" v "\"")
                                (name k)))
                            m)))

(defn ->closed-tag [tagname attrs]
  (if attrs
    (str "<" tagname " " (attributes attrs) ">")
    (str "<" tagname "/>")))

(defn ->opening-tag [tagname attrs]
  (if attrs
    (str "<" tagname " " (attributes attrs) ">")
    (str "<" tagname ">")))

(defn ->end-tag [tagname]
  (str "</" tagname ">"))

(defn h1 [& content]
  (let [attrs?  (map? (first content))
        content-items (if attrs? (rest content)
                          content)]
    (if (empty? content-items)
      (->closed-tag "h1" (when attrs? (first content)))
      (str
        (->opening-tag "h1" (when attrs? (first content)))
        (apply str content-items)
        (->end-tag "h1")))))

(defn tag-fn [tagname]
  (fn [& content]
    (let [attrs?  (map? (first content))
          real-content-items (if attrs? (rest content)
                            content)
          content-items (mapcat (fn [i]
                                  (if (sequential? i)
                                    i
                                    [i])) real-content-items)]
      (if (empty? content-items)
        (->closed-tag tagname (when attrs? (first content)))

        (str
          (->opening-tag tagname (when attrs? (first content)))
          (apply str content-items)
          (->end-tag tagname))))))

(defmacro define-html-tags [& tags]
  `(do
     ~@(map (fn [tagname]
              `(def ~(symbol tagname) (tag-fn ~tagname)))
            tags)))


(define-html-tags "h1" "h2" "h3" "h4" "h5" "p" "div" "span")


;;; --------------------------------------------------------------;;;
;;; End of the code from Exercise 11.02. What follows is the
;;; Exercise 11.03 code
;;; --------------------------------------------------------------;;;


(defn subtag-fn [tagname subtag]
  (fn subtag-function-builder
    ([content]
     (subtag-function-builder nil content))
    ([attrs content]
       (str
         (->opening-tag tagname attrs)
         (apply str (map subtag content))
         (->end-tag tagname)))))


(defmacro define-html-list-tags [& tags-with-subtags]
  `(do
     ~@(map (fn [[tagname subtag]]
              `(do
                 (def ~(symbol tagname) (tag-fn ~tagname))
                 (def ~(symbol (str tagname "->" subtag)) (subtag-fn ~tagname ~(symbol subtag)))))
            tags-with-subtags)))



(defmacro define-html-list-tags-with-mapcat [& tags-with-subtags]
  `(do
     ~@(mapcat (fn [[tagname subtag]]
                 [`(def ~(symbol tagname) (tag-fn ~tagname))
                  `(def ~(symbol (str tagname "->" subtag)) (subtag-fn ~tagname ~(symbol subtag)))])
               tags-with-subtags)))



(ns packt-clj.htmlgen
  (:require [clojure.string :as string]))

(defn attributes [m]
  (clojure.string/join " "
                       (map (fn [[k v]]
                              (if (string? v)
                                (str (name k) "=\"" v "\"")
                                (name k)))
                            m)))

(defn ->closed-tag [tagname attrs]
  (if attrs
    (str "<" tagname " " (attributes attrs) "/>")
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

;;; --------------------------------------------------------------;;;
;;; End of the code provided by your colleage. What follows is the
;;; Exercise code
;;; --------------------------------------------------------------;;;


(defmacro define-html-tags [& tags]
  `(do
     ~@(map (fn [tagname]
              `(def ~(symbol tagname) (tag-fn ~tagname)))
            tags)))


(define-html-tags "h1" "h2" "h3" "h4" "h5" "p" "div" "span")

;;; 
;;; Define functions for all the HTML 5 tags
;;; 
(comment
  (define-html-tags
    "a"
    "abbr"
    "acronym"
    "address"
    "applet"
    "area"
    "article"
    "aside"
    "audio"
    "b"
    "base"
    "basefont"
    "bb"
    "bdo"
    "big"
    "blockquote"
    "body"
    "br"
    "button"
    "canvas"
    "caption"
    "center"
    "cite"
    "code"
    "col"
    "colgroup"
    "command"
    "datagrid"
    "datalist"
    "dd"
    "del"
    "details"
    "dfn"
    "dialog"
    "dir"
    "div"
    "dl"
    "dt"
    "em"
    "embed"
    "eventsource"
    "fieldset"
    "figcaption"
    "figure"
    "font"
    "footer"
    "form"
    "frame"
    "frameset"
    "h1"
    "h2"
    "h3"
    "h4"
    "h5"
    "h6"
    "head"
    "header"
    "hgroup"
    "hr"
    "html"
    "i"
    "iframe"
    "img"
    "input"
    "ins"
    "isindex"
    "kbd"
    "keygen"
    "label"
    "legend"
    "li"
    "link"
    "map"
    "mark"
    "menu"
    "meta"
    "meter"
    "nav"
    "noframes"
    "noscript"
    "object"
    "ol"
    "optgroup"
    "option"
    "output"
    "p"
    "param"
    "pre"
    "progress"
    "q"
    "rp"
    "rt"
    "ruby"
    "s"
    "samp"
    "script"
    "section"
    "select"
    "small"
    "source"
    "span"
    "strike"
    "strong"
    "style"
    "sub"
    "sup"
    "table"
    "tbody"
    "td"
    "textarea"
    "tfoot"
    "th"
    "thead"
    "time"
    "title"
    "tr"
    "track"
    "tt"
    "u"
    "ul"
    "var"
    "video"
    "wbr"))

;; 1
; (doc clojure.string/replace)

;; 2
(clojure.string/replace "Hello World" #"\w" "!")

;; 3
(clojure.string/replace "Hello World" #"\w" (fn [letter] (do (println letter) "!")))

;; 4
(int \a)

;; 5 
(first (char-array "a"))

;; 6
(Math/pow (int (first (char-array "a"))) 2)

;; 7
(defn encode-letter [s]
  (let [code (Math/pow (int (first (char-array s))) 2)]
    (str (int code))))

(encode-letter "a")

;; 8
(defn encode [s]
  (clojure.string/replace s #"\w" encode-letter))

(encode "Hello World")

;; 9 
(defn encode-letter [s x]
  (let [code (Math/pow (+ x (int (first (char-array s)))) 2)]
    (str "#" (int code))))

;; 10 
; (encode "Hello World")

;; 11
(defn encode [s]
  (let [number-of-words (count (clojure.string/split s #" "))]
    (clojure.string/replace s #"\w" (fn [s] (encode-letter s number-of-words)))))
;; 12
(encode "Super secret")
(encode "Super secret message")

;; 13
(defn decode-letter [x y]
  (let [number (Integer/parseInt (subs x 1))
        letter (char (- (Math/sqrt number) y))]
    (str letter)))

;; 14
(defn decode [s]
  (let [number-of-words (count (clojure.string/split s #" "))]
    (clojure.string/replace s #"\#\d+" (fn [s] (decode-letter s number-of-words)))))

 ;; 15
 (encode "If you want to keep a secret, you must also hide it from yourself.")
 ; (decode *1)
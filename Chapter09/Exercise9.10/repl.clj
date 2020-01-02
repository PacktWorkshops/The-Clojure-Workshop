(def three-numbers-array (java.util.ArrayList. [0 1 2]))

(defn array-list-getter [array index]
      (.get array index))

(array-list-getter three-numbers-array 0)

(array-list-getter three-numbers-array 2)

(array-list-getter three-numbers-array 5)

(defn array-list-getter [array index]
      (try
        (.get array index)
        (catch IndexOutOfBoundsException ex
          (str "No element at index " index))
        (finally (println "Login usage of array-list-getter"))))

(array-list-getter three-numbers-array 1)

(array-list-getter three-numbers-array 5)
;; 1
(def booking [1425, "Bob Smith", "Allergic to unsalted peanuts only", [[48.9615, 2.4372], [37.742, -25.6976]], [[37.742, -25.6976], [48.9615, 2.4372]]])

;; 2
(let [[id customer-name sensitive-info flight1 flight2 flight3] booking] (println id customer-name flight1 flight2 flight3))

;; 3
(let [big-booking (conj booking [[37.742, -25.6976], [51.1537, 0.1821]] [[51.1537, 0.1821], [48.9615, 2.4372]])
      [id customer-name sensitive-info flight1 flight2 flight3] big-booking] (println id customer-name flight1 flight2 flight3))

;; 4
(let [[_ customer-name _ flight1 flight2 flight3] booking] (println customer-name flight1 flight2 flight3))

;; 5
(let [[_ customer-name _ & flights] booking]
  (println (str customer-name " booked " (count flights) " flights.")))

;; 6
(defn print-flight [flight]
  (let [[[lat1 lon1] [lat2 lon2]] flight]
    (println (str "Flying from: Lat " lat1 " Lon " lon1 " Flying to: Lat " lat2 " Lon " lon2))))

(print-flight [[48.9615, 2.4372], [37.742 -25.6976]])

;;  7
(defn print-flight [flight]
  (let [[departure arrival] flight
        [lat1 lon1] departure
        [lat2 lon2] arrival]
    (println (str "Flying from: Lat " lat1 " Lon " lon1 " Flying to: Lat " lat2 " Lon " lon2))))

(print-flight [[48.9615, 2.4372], [37.742 -25.6976]])

;; 8
(defn print-booking [booking]
  (let [[_ customer-name _ & flights] booking]
    (println (str customer-name " booked " (count flights) " flights."))
    (let [[flight1 flight2 flight3] flights]
      (when flight1 (print-flight flight1))
      (when flight2 (print-flight flight2))
      (when flight3 (print-flight flight3)))))

(print-booking booking)

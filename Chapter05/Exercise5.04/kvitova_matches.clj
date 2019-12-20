(def matches  
  [{:winner-name "Kvitova P.",
    :loser-name "Ostapenko J.",
    :tournament "US Open",
    :location "New York",
    :date "2016-08-29"}
   {:winner-name "Kvitova P.",
    :loser-name "Buyukakcay C.",
    :tournament "US Open",
    :location "New York",
    :date "2016-08-31"}
   {:winner-name "Kvitova P.",
    :loser-name "Svitolina E.",
    :tournament "US Open",
    :location "New York",
    :date "2016-09-02"}
   {:winner-name "Kerber A.",
    :loser-name "Kvitova P.",
    :tournament "US Open",
    :location "New York",
    :date "2016-09-05"}
   {:winner-name "Kvitova P.",
    :loser-name "Brengle M.",
    :tournament "Toray Pan Pacific Open",
    :location "Tokyo",
    :date "2016-09-20"}
   {:winner-name "Puig M.",
    :loser-name "Kvitova P.",
    :tournament "Toray Pan Pacific Open",
    :location "Tokyo",
    :date "2016-09-21"}])


;;; In REPL
(def matches-by-date (zipmap (map :date matches) matches))
(get matches-by-date "2016-09-20")

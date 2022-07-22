(require '[clojure.test :as test :refer [are deftest run-tests]])

(load-file "../../Activity1.03/repl.clj")

(deftest meditation-test
  (are [x y] (= x y)
    "WHAT WE DO NOW ECHOES IN ETERNITY, I TELL YA!" (meditate 1 "what we do now echoes in eternity")
    "WHAT WE DO NOW ECHOES IN ETERNITY, I TELL YA!" (meditate 4 "what we do now echoes in eternity")
    "What we do now echoes in eternity" (meditate 5 "what we do now echoes in eternity")
    "What we do now echoes in eternity" (meditate 6 "what we do now echoes in eternity")
    "What we do now echoes in eternity" (meditate 9 "what we do now echoes in eternity")
    "ytinrete ni seohce won od ew tahw" (meditate 10 "what we do now echoes in eternity")
    nil (meditate 50 "what we do now echoes in eternity")))

 (run-tests)

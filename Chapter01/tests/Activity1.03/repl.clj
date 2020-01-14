(require '[clojure.test :as test :refer [are deftest run-tests]])

(load-file "../../Activity1.03/repl.clj")

(deftest meditation-test
  (are [x y] (= x y)
    "WHAT WE DO NOW ECHOES IN ETERNITY, I TELL YA!" (meditate "what we do now echoes in eternity" 1)
    "What we do now echoes in eternity" (meditate "what we do now echoes in eternity" 6)
    "ytinrete ni seohce won od ew tahw" (meditate "what we do now echoes in eternity" 10)
    nil (meditate "what we do now echoes in eternity" 50)))

 (run-tests)

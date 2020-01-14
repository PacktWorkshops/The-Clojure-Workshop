(require '[clojure.test :as test :refer [is are deftest run-tests]])

(load-file "../../Exercise2.01/repl.clj")

(deftest exercise-201-test
  (is (= "!!!!! !!!!!" (clojure.string/replace "Hello World" #"\w" "!")))
  (is (= 97 (int \a)))
  (is (= \a (first (char-array "a"))))
  (is (= 9409.0 (Math/pow (int (first (char-array "a"))) 2)))
  (is (= "#7225#14161#12996#10609#13456 #13689#10609#10201#13456#10609#13924" (encode "Super secret")))
  (is (= "#7396#14400#13225#10816#13689 #13924#10816#10404#13689#10816#14161 #12544#10816#13924#13924#10000#11236#10816")
         (encode "Super secret message"))
  (is (= "#7569#13456 #18225#15625#17161 #17689#12321#15376#16900 #16900#15625 #14641#13225#13225#15876 #12321 #16641#13225#12769#16384#13225#16900, #18225#15625#17161 #15129#17161#16641#16900 #12321#14884#16641#15625 #13924#14161#12996#13225 #14161#16900 #13456#16384#15625#15129 #18225#15625#17161#16384#16641#13225#14884#13456."
         (encode "If you want to keep a secret, you must also hide it from yourself.")))
  (is (= "If you want to keep a secret, you must also hide it from yourself." (decode (encode "If you want to keep a secret, you must also hide it from yourself.")))))


(run-tests)

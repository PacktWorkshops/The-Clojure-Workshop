(ns packt-clj.groceries)

(def grocery-articles [{:name "Flour"
                         :weight 1000      ; grams
                         :max-dimension 140 ; millimeters
                        }
                       {:name "Bread"
                        :weight 350
                        :max-dimension 250}
                       {:name "Potatoes"
                        :weight 2500
                        :max-dimension 340}
                       {:name "Pepper"
                        :weight 85
                        :max-dimension 90}
                       {:name "Ice cream"
                        :weight 450
                        :max-dimension 200}
                       {:name "Green beans"
                        :weight 300
                        :max-dimension 120}
                       {:name "Olive oil"
                        :weight 400
                        :max-dimension 280}])

(defn article-stream [n] 
 (repeatedly n #(rand-nth grocery-articles)))

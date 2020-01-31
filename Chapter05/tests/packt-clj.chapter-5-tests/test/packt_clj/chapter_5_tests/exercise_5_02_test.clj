(ns packt-clj.chapter-5-tests.exercise-5-02-test
  (:require
   [clojure.test :as t :refer [deftest is testing]]
   [packt-clj.chapter-5-tests.exercise-5-02 :as exo]))

(deftest same-slope
  (is (exo/same-slope-as-current? [[1 5] [2 10]] 15) "The slope keeps going up")
  (is (not (exo/same-slope-as-current? [[1 5] [2 10]] 5)) "The slope goes up to 10, then back to 5")
  (is (exo/same-slope-as-current? [[1 5] [2 10]] 10) "The slope goes up to 10 then levels off")
  (is (exo/same-slope-as-current? [[1 5]] 10) "Just to points, so has to be same slope")
  (is (exo/same-slope-as-current? [[1 5] [2 10] [3 15]] 20) "The slope keeps going up"))


(deftest distances-elevation-for-peaks-valleys
  (testing "with simple data"
    (let [simple-data [[1 5] [2 10] [3 15]]]
      (is (= [{:race-position 1,          
               :elevation 5,
               :distance-to-next 2,
               :elevation-to-next 10}
              {:race-position 2,
               :elevation 10,
               :distance-to-next 1,
               :elevation-to-next 5}
              {:race-position 3,
               :elevation 15,
               :distance-to-next 0,
               :elevation-to-next 0}]
             (exo/distances-elevation-to-next-peak-or-valley simple-data)))))

  
  (let [result (exo/distances-elevation-to-next-peak-or-valley exo/distance-elevation)]
    (is (=
          [{:race-position 0          
            :elevation 400
            :distance-to-next 19
            :elevation-to-next 222}
           {:race-position 12.5
            :elevation 457
            :distance-to-next 6.5
            :elevation-to-next 165}
           {:race-position 19
            :elevation 622
            :distance-to-next 2.5
            :elevation-to-next -30}
           {:race-position 21.5
            :elevation 592
            :distance-to-next 21.5
            :elevation-to-next 885}
           {:race-position 29
            :elevation 615
            :distance-to-next 14
            :elevation-to-next 862}
           {:race-position 35.5
            :elevation 892
            :distance-to-next 7.5
            :elevation-to-next 585}
           {:race-position 39
            :elevation 1083
            :distance-to-next 4
            :elevation-to-next 394}
           {:race-position 43
            :elevation 1477
            :distance-to-next 19.5
            :elevation-to-next -747}
           {:race-position 48.5
            :elevation 1151
            :distance-to-next 14.0
            :elevation-to-next -421}
           {:race-position 52.5
            :elevation 999
            :distance-to-next 10.0
            :elevation-to-next -269}
           {:race-position 57.5
            :elevation 800
            :distance-to-next 5.0
            :elevation-to-next -70}
           {:race-position 62.5
            :elevation 730
            :distance-to-next 8.0
            :elevation-to-next 703}
           {:race-position 65
            :elevation 1045
            :distance-to-next 5.5
            :elevation-to-next 388}
           {:race-position 68.5
            :elevation 1390
            :distance-to-next 2.0
            :elevation-to-next 43}
           {:race-position 70.5
            :elevation 1433
            :distance-to-next 13.5
            :elevation-to-next -766}
           {:race-position 75
            :elevation 1211
            :distance-to-next 9
            :elevation-to-next -544}
           {:race-position 78.5
            :elevation 917
            :distance-to-next 5.5
            :elevation-to-next -250}
           {:race-position 82.5
            :elevation 744
            :distance-to-next 1.5
            :elevation-to-next -77}
           {:race-position 84
            :elevation 667
            :distance-to-next 4.5
            :elevation-to-next 193}
           {:race-position 88.5
            :elevation 860
            :distance-to-next 19.5
            :elevation-to-next -458}
           {:race-position 96
            :elevation 671
            :distance-to-next 12
            :elevation-to-next -269}
           {:race-position 99
            :elevation 584
            :distance-to-next 9
            :elevation-to-next -182}
           {:race-position 108
            :elevation 402
            :distance-to-next 7.5
            :elevation-to-next 71}
           {:race-position 115.5
            :elevation 473
            :distance-to-next 0
            :elevation-to-next 0}]
          result))))

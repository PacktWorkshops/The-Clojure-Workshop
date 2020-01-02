(ns hello-test.core-test
  (:require [cljs.test :refer-macros [are async deftest is testing]]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :refer-macros [for-all]]
            [clojure.test.check.clojure-test :refer-macros [defspec]]
            [cuerdas.core :as str]
            [hello-test.core :refer [adder]]))
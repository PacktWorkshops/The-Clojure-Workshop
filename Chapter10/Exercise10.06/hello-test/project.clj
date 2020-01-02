(defproject hello-test "0.1.0-SNAPSHOT"
            :description "Testing in ClojureScript"
            :dependencies [[org.clojure/clojure "1.10.0"]
                           [org.clojure/clojurescript "1.10.520"]
                           [cljs-http "0.1.46"]
                           [org.clojure/test.check "0.10.0"]
                           [funcool/cuerdas "2.2.0"]]
            :plugins [[lein-doo "0.1.11"]]

            :cljsbuild {:builds
                        {:test {:source-paths ["src" "test"]
                                :compiler {:output-to "out/tests.js"
                                           :output-dir "out"
                                           :main hello-test.runner
                                           :optimizations :none}}}})
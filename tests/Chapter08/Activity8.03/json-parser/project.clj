(defproject json-parser "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [cheshire "3.0.0"]]
  :main ^:skip-aot json-parser.core
  :target-path "target/%s"
  :profiles {:qa      {:dependencies [[expectations "2.1.10"]]
                       :plugins      [[lein-expectations "0.0.8"]]}
             :uberjar {:aot :all}})

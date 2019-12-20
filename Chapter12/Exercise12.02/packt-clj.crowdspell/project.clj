(defproject packt-clj.crowdspell "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/tools.cli "0.4.2"]
                 [clj-http "3.10.0"]
                 [org.clojure/data.json "0.2.6"]]
  :main ^:skip-aot packt-clj.crowdspell
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})

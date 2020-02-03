(defproject packt-clj.chapter-7-tests "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [semantic-csv "0.2.1-alpha1"]
                 [org.clojure/data.csv "0.1.4"]
                 [org.clojure/math.numeric-tower "0.0.4"]]
  :repl-options {:init-ns packt-clj.chapter-7-tests}
  :profiles {:dev {:resource-paths ["test/resources"]}})

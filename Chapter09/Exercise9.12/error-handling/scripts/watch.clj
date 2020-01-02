(require '[cljs.build.api :as b])

(b/watch "src"
  {:main 'error-handling.core
   :output-to "out/error_handling.js"
   :output-dir "out"})

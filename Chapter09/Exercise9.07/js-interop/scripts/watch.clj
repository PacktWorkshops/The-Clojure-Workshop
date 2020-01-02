(require '[cljs.build.api :as b])

(b/watch "src"
  {:main 'js-interop.core
   :output-to "out/js_interop.js"
   :output-dir "out"})

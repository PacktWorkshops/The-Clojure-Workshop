(import 'java.time.ZonedDateTime)

(. (. (ZonedDateTime/now) getOffset) getTotalSeconds)

(.. (ZonedDateTime/now) getOffset getTotalSeconds)

(let [string (StringBuffer. "quick")]
     (.append string " brown")
     (.append string " fox")
     (.append string " jumped")
     (.append string " over")
     (.append string " the")
     (.append string " lazy")
     (.append string " dog")
     (.toString string))

(let [string (StringBuffer. "quick")]
     (doto string
           (.append " brown")
           (.append " fox")
           (.append " jumped")
           (.append " over")
           (.append " the")
           (.append " lazy")
           (.append " dog"))
     (.toString string))
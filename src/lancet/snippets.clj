; not executable! don't load this

; START: set-property!
(use '[clojure.contrib.except :only (throw-if)])
(defn set-property! [inst prop value]
  (let [pd (property-descriptor inst prop)]   
    (throw-if (nil? pd) (str "No such property " prop)) 
    (.invoke (.getWriteMethod pd) inst (into-array [value])))) ; <label id="sequences.lancet.value"/> 
; END: set-property!


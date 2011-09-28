(ns examples.macros.chain-1)
; START: chain
; chain reimplements Clojure's .. macro
(defmacro chain [x form]
  (list '. x form))
; END: chain

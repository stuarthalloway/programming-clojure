(ns examples.macros.chain-3)
; START: chain
(defmacro chain [x form]
  `(. ~x ~form))
; END: chain
(ns examples.macros.chain-5)
; START: chain
(defmacro chain
  ([x form] `(. ~x ~form))
  ([x form & more] `(chain (. ~x ~form) ~@more)))
; END: chain

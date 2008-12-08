(ns examples.macros.chain-5)
; START: chain
(defmacro chain
  ([x form] `(. ~x ~form))
  ([x form & rest] `(chain (. ~x ~form) ~@rest)))
; END: chain

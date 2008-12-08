(ns examples.macros.chain-4)
; START: chain
; Does not quite work
(defmacro chain
  ([x form] `(. ~x ~form))
  ([x form & rest] `(chain (. ~x ~form) ~rest)))
; END: chain
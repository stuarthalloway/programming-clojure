(ns examples.macros.chain-2)
; START: chain
(defmacro chain 
  ([x form] (list '. x form))
  ([x form & more] (concat (list 'chain (list '. x form)) more)))
; END: chain

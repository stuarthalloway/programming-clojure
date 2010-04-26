(ns examples.multimethods.default)

; START: multimethod-default
(defmulti my-print class :default :everything-else)
(defmethod my-print String [s]
  (.write *out* s))
(defmethod my-print :everything-else [_]
  (.write *out* "Not implemented yet..."))
; END: multimethod-default

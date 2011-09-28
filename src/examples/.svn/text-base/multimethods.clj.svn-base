(ns examples.multimethods)

; START: defmulti
(defmulti my-print class)
; END: defmulti

(defn my-println [ob]
  (my-print ob)
  (.write *out* "\n"))

; START: defmethod-string
(defmethod my-print String [s]
  (.write *out* s))
; END: defmethod-string

; START: defmethod-nil
(defmethod my-print nil [s]
  (.write *out* "nil"))
; END: defmethod-nil

; START: defmethod-number
(defmethod my-print Number [n]
  (.write *out* (.toString n)))
; END: defmethod-number

; START: defmethod-default
(defmethod my-print :default [s]
  (.write *out* "#<")
  (.write *out* (.toString s))
  (.write *out* ">"))
; END: defmethod-default

; START: defmethod-collection
(require '[clojure.string :as str])
(defmethod my-print java.util.Collection [c]
  (.write *out* "(")
  (.write *out* (str/join " " c))
  (.write *out* ")"))
; END: defmethod-collection

; START: defmethod-vector
(defmethod my-print clojure.lang.IPersistentVector [c]
  (.write *out* "[")
  (.write *out* (str/join " " c))
  (.write *out* "]"))
; END: defmethod-vector

; START:prefer-method
(prefer-method 
 my-print clojure.lang.IPersistentVector java.util.Collection)  
; END:prefer-method

; START: my-class
(defmulti my-class identity)
(defmethod my-class nil [_] nil)
(defmethod my-class :default [x] (.getClass x))
; END: my-class


(ns examples.life-without-multi)

; START: my-println-1
(defn my-print [ob]
  (.write *out* ob))
; END: my-println-1
(def my-print-1 my-print)

; START: my-println
(defn my-println [ob]
  (my-print ob)
  (.write *out* "\n"))
; END: my-println

; START: my-println-2
(defn my-print [ob]
  (cond
   (nil? ob) (.write *out* "nil")
   (string? ob) (.write *out* ob)))
; END: my-println-2

(def my-print-2 my-print)

; START: my-println-3
(require '[clojure.string :as str])
(defn my-print-vector [ob]
  (.write *out*"[")
  (.write *out* (str/join " " ob))
  (.write *out* "]"))

(defn my-print [ob] 
  (cond
   (vector? ob) (my-print-vector ob)
   (nil? ob) (.write *out* "nil")
   (string? ob) (.write *out* ob)))
; END: my-println-3

(def my-print-3 my-print)

(ns examples.macros)

; START: unless-1
; This is doomed to fail...
(defn unless [expr form]
  (if expr nil form))
; END: unless-1
(def unless-1 unless)

; START: unless-2
(defn unless [expr form]
  (println "About to test...")
  (if expr nil form))
; END: unless-2
(def unless-2 unless)

; START: unless-3
(defmacro unless [expr form]
  (list 'if expr nil form))
; END: unless-3

; START: unless-4
(defmacro bad-unless [expr form]
  (list 'if 'expr nil form))
; END: unless-4

; START: with-out-str-as-fn
(defn with-out-str-as-fn [f]
  (let [s# (new java.io.StringWriter)]
    (binding [*out* s#]
      (f)           
      (str s#))))
; END: with-out-str-as-fn

; Don't tell Rich I showed you how to do this.
(defmacro evil-bench [expr]
  `(let [~'start (System/nanoTime)
	 ~'result ~expr]
     {:result ~'result :elapsed (- (System/nanoTime) ~'start)}))

; START: bench-2
(defmacro bench [expr]
  `(let [start# (System/nanoTime)
	 result# ~expr]
     {:result result# :elapsed (- (System/nanoTime) start#)}))
; END: bench-2	      

; START: bench-fn
(defn bench-fn [f]
  (let [start (System/nanoTime)
	result (f)]
     {:result result :elapsed (- (System/nanoTime) start)}))
; END: bench-fn
  





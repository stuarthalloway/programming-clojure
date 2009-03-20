(ns examples.memoized-male-female)

; Hofstadter's Male and Female Sequences from GEB
; See http://en.wikipedia.org/wiki/Hofstadter_sequence
(declare m f)
(defn- m [n]
  (if (zero? n)
    0
    (- n (f (m (dec n))))))

(defn- f [n]		 
  (if (zero? n)
    1
    (- n (m (f (dec n))))))

; START: m-f-memoize 
(def m (memoize m))
(def f (memoize f))
; END: m-f-memoize


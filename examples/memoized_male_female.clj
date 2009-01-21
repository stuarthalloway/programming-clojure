(ns examples.memoized-male-female)

(declare m f)
(defn- m [n]
  (if (= n 0)
    0
    (- n (f (m (dec n))))))

(defn- f [n]		 
  (if (= n 0)
    1
    (- n (m (f (dec n))))))

; START: m-f-memoize 
(def m (memoize m))
(def f (memoize f))
; END: m-f-memoize


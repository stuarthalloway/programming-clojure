(ns examples.male-female-seq)

(declare m f)
(defn- m [n]
  (if (zero? n)
    0
    (- n (f (m (dec n))))))

(defn- f [n]		 
  (if (zero? n)
    1
    (- n (m (f (dec n))))))

(def ^:private m (memoize m))
(def ^:private f (memoize f))

; START: m-f-seq
(def m-seq (map m (iterate inc 0)))
(def f-seq (map f (iterate inc 0)))
; END: m-f-seq


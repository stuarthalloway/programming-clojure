(ns examples.male-female)

; START: m-f
; do not use these directly
(declare m f)
(defn m [n]
  (if (zero? n)
    0
    (- n (f (m (dec n))))))
(defn f [n]		 
  (if (zero? n)
    1
    (- n (m (f (dec n))))))
; END: m-f


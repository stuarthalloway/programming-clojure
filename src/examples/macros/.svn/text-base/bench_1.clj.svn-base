(ns examples.macros.bench-1)

; START: bench
; This won't work
(defmacro bench [expr]
  `(let [start (System/nanoTime)
	 result ~expr]
     {:result result :elapsed (- (System/nanoTime) start)}))
; END: bench	      



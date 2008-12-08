(ns lancet.defrunonce-1)

; START: defrunonce
(defmacro defrunonce [sym doc & forms]
  `(let [[has-run-fn reset-fn once-fn] (runonce (fn [] ~@forms))]
     (def ~(with-meta sym {:has-run-fn has-run-fn :reset-fn reset-fn :doc doc}) 
	  once-fn)))
; END: defrunonce
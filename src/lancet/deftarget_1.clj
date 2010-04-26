(ns lancet.deftarget-1)
; broken for demo purposes, correct version is in lancet/step_4_repl.clj

; START: deftarget
(defmacro deftarget [sym doc & forms]
  `(let [[has-run-fn reset-fn once-fn] (runonce (fn [] ~@forms))]
     (def ~(with-meta sym {:has-run-fn has-run-fn 
			   :reset-fn reset-fn 
			   :doc doc}) 
	  once-fn)))
; END: deftarget
(ns lancet.step-3-complete)

; START: runonce
(defn runonce
 "Create a function that will only run once. All other invocations
 return the first calculated value. The function can have side effects.
 Returns a [has-run-predicate, reset-fn, once-fn]"
 [function]
 (let [sentinel (Object.) 
       result (atom sentinel)
       reset-fn (fn [] (reset! result sentinel) nil) ; <label id="concurrency.lancet.reset-fn"/>
       has-run? #(not= @result sentinel)] ; <label id="concurrency.lancet.has-run"/>
   [has-run?
    reset-fn
    (fn [& args]
      (locking sentinel
	(if (= @result sentinel) 
	  (reset! result (function)) 
	  @result)))]))
; END: runonce






		 


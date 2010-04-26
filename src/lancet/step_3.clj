(defn runonce
 "Create a function that will only run once. All other invocations
 return the first calculated value. The function can have side effects.
 Returns a [has-run-predicate, reset-fn, once-fn]"
 [function]
 (let [sentinel (Object.) 
       result (atom sentinel)
       reset-fn (fn [] (reset! result sentinel)) 
       has-run? #(not= @result sentinel)] 
   [has-run?
    reset-fn
    (fn [& args]
      (locking sentinel
	(if (= @result sentinel) 
	  (reset! result (function)) 
	  @result)))]))





		 


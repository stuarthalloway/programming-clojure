(ns lancet.step-3-complete)

(defn runonce
 "Create a function that will only run once. All other invocations
 return the first calculated value. The function can have side effects.
 Returns a [has-run-predicate, reset-fn, once-fn]"
 [function]
 (let [sentinel (Object.) 
       agt (agent sentinel)
       reset-fn (fn [] (send agt (fn [_] sentinel)) (await agt))
       has-run? #(not= @agt sentinel)]
   [has-run?
    reset-fn
    (fn [& args] 
      (when (= @agt sentinel)
	(send-off agt
		  #(if (= % sentinel)
		     (apply function args)
		     %))
	(await agt))
      @agt)]))






		 


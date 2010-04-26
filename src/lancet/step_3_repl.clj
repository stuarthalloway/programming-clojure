(ns lancet.step-3-repl)

; START: create-runonce
(defn create-runonce [function]
  (let [sentinel (Object.) ; <label id="concurrency.lancet.sentinel"/>
	result (atom sentinel)] ; <label id="concurrency.lancet.atom"/>
    (fn [& args]
      (locking sentinel ; <label id="concurrency.lancet.locking"/>
	(if (= @result sentinel) 
	  (reset! result (function)) ; <label id="concurrency.lancet.call"/>
	  @result))))) ; <label id="concurrency.lancet.cache"/>
; END: create-runonce





		 


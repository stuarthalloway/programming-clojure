(ns lancet.step-3-repl)

; START: create-runonce
(defn create-runonce [function agt]
  (let [sentinel @agt] ; <label id="concurrency.lancet.sentinel"/>
    (fn [& args]
      (send-off agt   ; <label id="concurrency.lancet.send-off"/>
		#(if (= % sentinel) ; <label id="concurrency.lancet.check"/>
		   (apply function args) ; <label id="concurrency.lancet.apply"/>
		   %))
      (await agt)                       ; <label id="concurrency.lancet.await"/>
      @agt)))                             ; <label id="concurrency.lancet.deref"/>
; END: create-runonce

; START: peek
(defn create-runonce-with-peek [function agt]
  (let [sentinel @agt]
    (fn [& args]
      (when (= @agt sentinel) ; <label id="concurrency.lancet.peek"/>
	(send-off agt
		  #(if (= % sentinel) ; <label id="concurrency.lancet.check-again"/>
		     (apply function args)
		     %))
	(await agt))
      @agt)))  
; END: peek

; START: create-has-run-predicate
(defn create-has-run-predicate [agt, sentinel]
  (fn []
    (not= @agt sentinel)))
; END: create-has-run-predicate 

; START: reset-runonce
(defn reset-runonce [agt, sentinel]
  (send agt (fn [_] sentinel))
  (await agt))
; END: reset-runonce

; START: runonce
(defn runonce
 [function]
 (let [sentinel (Object.) ; <label id="concurrency.lancet.object"/>
       agt (agent sentinel)
       reset-fn (fn [] (send agt (fn [_] sentinel)) (await agt))
       has-run? #(not= @agt sentinel)]
   [has-run?
    reset-fn
    (fn [& args] ; <label id="concurrency.lancet.anon"/>
      (when (= @agt sentinel)
	(send-off agt
		  #(if (= % sentinel)
		     (apply function args)
		     %))
	(await agt))
      @agt)]))
; END: runonce




		 


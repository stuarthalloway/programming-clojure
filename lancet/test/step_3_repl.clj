(ns lancet.test.step-3-repl
  (:use clojure.contrib.test-is)
  (:use lancet.step-3-repl))

(def counter (ref 0))
(defn inc-counter [] (dosync (alter counter inc)))
(defn zero-counter! [] (dosync (ref-set counter 0)))

(deftest test-create-runonce
  (zero-counter!)
  (let [agt (agent nil)
	inc-once (create-runonce inc-counter agt)]
    (is (= @counter 0))
    (inc-once)
    (is (= @counter 1) "function executes once")
    (is (= @agt 1))
    (inc-once)
    (is (= @counter 1) "function does not execute again")
))
     
(deftest test-create-runonce-with-peek
  (zero-counter!)
  (let [agt (agent nil)
	inc-once (create-runonce-with-peek inc-counter agt)]
    (is (= @counter 0))
    (inc-once)
    (is (= @counter 1) "function executes once")
    (is (= @agt 1))
    (inc-once)
    (is (= @counter 1) "function does not execute again")
))

(deftest test-create-has-run-predicate
  (let [agt (agent 0)
	has-run? (create-has-run-predicate agt 0)]
    (is (false? (has-run?)))
    (send agt inc)
    (await agt)
    (is (has-run?))))
    
(deftest test-reset-runonce
  (let [agt (agent :has-run)]
    (is (= @agt :has-run))
    (reset-runonce agt :has-not-run)
    (is (= @agt :has-not-run))))
	

(deftest test-runonce
  (zero-counter!)
  (let [[has-run? reset f] (runonce inc-counter)]

    ; TODO: add nested contexts to test-is, a la PCL
    ; initial state
    (each= 
     (has-run?) false
     @counter 0)
    
    ; run the fn
    (f)
    (each=
     (has-run?) true
     @counter 1)

    ; run the fn again (no change)
    (f)
    (each=
     (has-run?) true
     @counter 1)

    ; reset the fn
    (reset)
    (each= 
     (has-run?) false)

    ; run the fn again
    (f)
    (each=
     (has-run?) true
     @counter 2)
))


  
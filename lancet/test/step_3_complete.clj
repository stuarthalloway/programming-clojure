(ns lancet.test.step-3-complete
  (:use clojure.contrib.test-is)
  (:use lancet.step-3-complete))

(def counter (ref 0))
(defn inc-counter [] (dosync (alter counter inc)))
(defn zero-counter! [] (dosync (ref-set counter 0)))

(deftest test-runonce
  (zero-counter!)
  (let [[has-run? reset f] (runonce inc-counter)]

    ; TODO: add nested contexts to test-is, a la PCL
    ; initial state
    (is (= (has-run?) false))
    (is (= @counter 0))
    
    ; run the fn
    (f)
    (is (= (has-run?) true))
    (is	(= @counter 1))

    ; run the fn again (no change)
    (f)
    (is (= (has-run?) true))
    (is (= @counter 1))

    ; reset the fn
    (reset)
    (is (= (has-run?) false))

    ; run the fn again
    (f)
    (is (= (has-run?) true))
    (is (= @counter 2))))


  
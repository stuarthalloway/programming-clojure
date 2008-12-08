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
    (are = 
     (has-run?) false
     @counter 0)
    
    ; run the fn
    (f)
    (are =
     (has-run?) true
     @counter 1)

    ; run the fn again (no change)
    (f)
    (are =
     (has-run?) true
     @counter 1)

    ; reset the fn
    (reset)
    (are = 
     (has-run?) false)

    ; run the fn again
    (f)
    (are =
     (has-run?) true
     @counter 2)
))


  
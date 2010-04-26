(ns lancet.test.step-3-repl
  (:use clojure.contrib.test-is)
  (:use lancet.step-3-repl))

(def counter (ref 0))
(defn inc-counter [] (dosync (alter counter inc)))
(defn zero-counter! [] (dosync (ref-set counter 0)))

(deftest test-create-runonce
  (zero-counter!)
  (let [inc-once (create-runonce inc-counter)]
    (is (= @counter 0))
    (inc-once)
    (is (= @counter 1) "function executes once")
    (inc-once)
    (is (= @counter 1) "function does not execute again")
))
     

  
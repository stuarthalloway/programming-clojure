(ns examples.test.macros
  (:use clojure.contrib.test-is examples.macros))

; unless-1 evals args before test
(deftest test-unless-1
  (throws Exception (unless-1 false (throw (Exception.))))
  (throws Exception (unless-1 true (throw (Exception.))))
)

; unless-2 evals args before test
(deftest test-unless-2
  (each= 
   (with-out-str (unless-2 false :foo)) "About to test...\n"
   (with-out-str (unless-2 true :foo)) "About to test...\n"))

; unless-3 is a macro and does the right thing   
(deftest test-unless
  (throws Exception (unless false (throw (Exception.))))
  (unless true (throw (Exception.)))
)

; bad-unless captures a symbol
(deftest test-expansions
  (each=
   (macroexpand-1 '(examples.macros/unless false :foo)) '(if false nil :foo)
   (macroexpand-1 '(examples.macros/bad-unless false :foo)) '(if expr nil :foo)
  )
)

(deftest test-bench-2
  (each= 
   (:result (examples.macros/bench (+ 1 2)))
   3))

(deftest test-bench-fn
  (each=
   (:result (examples.macros/bench-fn (fn [] (+ 2 2))))
   4))
				      
	    

  
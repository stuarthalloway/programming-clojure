(ns examples.test.macros.bench-1
  (:use clojure.test examples.macros.bench-1))

(deftest test-bench-1
  (are [x y] (= x y)
   (macroexpand-1 '(examples.macros.bench-1/bench :foo))
   '(clojure.core/let [examples.macros.bench-1/start (java.lang.System/nanoTime) 
	 	       examples.macros.bench-1/result :foo] 
      {:elapsed (clojure.core/- (java.lang.System/nanoTime) examples.macros.bench-1/start), 
       :result examples.macros.bench-1/result})))


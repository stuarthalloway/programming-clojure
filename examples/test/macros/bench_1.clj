(ns examples.test.macros.bench-1
  (:use clojure.contrib.test-is examples.macros.bench-1))

(deftest test-bench-1
  (are (= _1 _2)
   (macroexpand-1 '(examples.macros.bench-1/bench :foo))
   '(clojure.core/let [examples.macros.bench-1/start (System/nanoTime) 
	 	       examples.macros.bench-1/result :foo] 
      {:elapsed (clojure.core/- (System/nanoTime) examples.macros.bench-1/start), 
       :result examples.macros.bench-1/result})))


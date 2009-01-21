(ns examples.test.symbol-replace
  (:use clojure.contrib.test-is 
        examples.symbol-replace))

(deftest test-symbol-replace
  (are (= _1 _2)
       () (symbol-replace () 'a 'b)
       '(a) (symbol-replace '(a) 'b 'c)
       '(c) (symbol-replace '(b) 'b 'c)
       '(a (d e)) (symbol-replace '(a (d e)) 'b 'c)
       '(c (c c)) (symbol-replace '(b (b b)) 'b 'c)
))


(ns examples.test.replace-symbol
  (:use clojure.test 
        examples.replace-symbol))

(deftest test-replace-symbol
  (are [x y] (= x y)
       () (replace-symbol () 'a 'b)
       '(a) (replace-symbol '(a) 'b 'c)
       '(c) (replace-symbol '(b) 'b 'c)
       '(a (d e)) (replace-symbol '(a (d e)) 'b 'c)
       '(c (c c)) (replace-symbol '(b (b b)) 'b 'c)
       '((a a) (((a g r) (f r)) c (d e)) a)
       (replace-symbol '((a b) (((b g r) (f r)) c (d e)) b) 'b 'a)))


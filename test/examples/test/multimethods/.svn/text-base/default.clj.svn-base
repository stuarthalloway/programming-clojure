(ns examples.test.multimethods.default
  (:use clojure.test examples.multimethods.default))

(deftest test-my-print
  (are [x y] (= x y)
   (with-out-str (my-print "foo")) "foo"
   (with-out-str (my-print 42)) "Not implemented yet..."))


  

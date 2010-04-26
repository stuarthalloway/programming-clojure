(ns examples.test.multimethods.default
  (:use clojure.contrib.test-is)
  (:use examples.multimethods.default))

(deftest test-my-print
  (are (= _1 _2)
   (with-out-str (my-print "foo")) "foo"
   (with-out-str (my-print 42)) "Not implemented yet..."
))


  
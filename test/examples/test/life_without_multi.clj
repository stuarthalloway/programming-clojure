(ns examples.test.life-without-multi
  (:use clojure.contrib.test-is)
  (:use examples.life-without-multi))

(deftest test-my-println 
  (is (= (with-out-str (my-println "foo")) "foo\n"))
)

(deftest test-my-print
  (is (thrown? NullPointerException (my-print-1 nil)))
  (are (= _1 _2)
   (with-out-str (my-print-1 "foo")) "foo"

   (with-out-str (my-print-2 "foo")) "foo"
   (with-out-str (my-print-2 nil)) "nil"
   (with-out-str (my-print-2 [1 2 3])) ""

   (with-out-str (my-print-3 "foo")) "foo"
   (with-out-str (my-print-3 nil)) "nil"
   (with-out-str (my-print-3 [1 2 3])) "[1 2 3]")
)
    



  
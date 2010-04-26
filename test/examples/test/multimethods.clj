(ns examples.test.multimethods
  (:use clojure.contrib.test-is)
  (:use examples.multimethods))

(deftest test-my-println 
  (is (= (with-out-str (my-println "foo")) "foo\n"))
)

(deftest test-my-print
  (let [my-print-str (fn [& args] (with-out-str (apply my-print args)))]
    (are (= _1 _2)
     (my-print-str "strval") "strval"
     (my-print-str nil) "nil"
     (my-print-str 42) "42"
     (my-print-str '(1 2 3)) "(1 2 3)"
     (my-print-str [4 5 6]) "[4 5 6]"
     (my-print-str (java.io.File. "foo")) "#<foo>"
)))

(deftest test-my-class
  (are (= _1 _2)
   String (my-class "foo")
   nil (my-class nil)
))
     



  
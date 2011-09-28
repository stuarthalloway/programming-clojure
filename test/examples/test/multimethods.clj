(ns examples.test.multimethods
  (:use clojure.test examples.multimethods))

(deftest test-my-println 
  (is (= (with-out-str (my-println "foo")) "foo\n")))

(deftest test-my-print
  (let [my-print-str (fn [& args] (with-out-str (apply my-print args)))]
    (are [x y] (= x y)
     (my-print-str "strval") "strval"
     (my-print-str nil) "nil"
     (my-print-str 42) "42"
     (my-print-str '(1 2 3)) "(1 2 3)"
     (my-print-str [4 5 6]) "[4 5 6]"
     (my-print-str (java.io.File. "foo")) "#<foo>")))

(deftest test-my-class
  (are [x y] (= x y)
   String (my-class "foo")
   nil (my-class nil)))
     



  

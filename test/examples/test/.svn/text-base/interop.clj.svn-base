(ns examples.test.interop
  (:use clojure.test)
  (:use examples.interop))

(deftest sum-to-variants
  (is (= (sum-to 10) 55))
  (is (= (integer-sum-to 10) 55))
  (is (= (unchecked-sum-to 10) 55))
  (is (= (better-sum-to 10) 55))
  (is (= (best-sum-to 10) 55))
)

(deftest test-painstakingly-create-array
  (is (= (seq (painstakingly-create-array))
	 ["Painstaking" "to" "fill" "in" "arrays"])))

(deftest sax-parsing
  (is (= (with-out-str (demo-sax-parse "<foo><bar>hello</bar></foo>" print-element-handler))
	 "Saw element: foo\nSaw element: bar\n")))

;; skipping test of demo-threads
;; to write this test you would need a cross-thread with-out-str

(deftest test-try-finally
  (is (= (with-out-str (is (thrown? Exception (demo-try-finally))))
	 "we get to clean up\n"))
)

(deftest test-class-available
  (is (thrown? ClassNotFoundException (poor-class-available? "java.lang.MicrosoftRocks")))
  (is (= String (poor-class-available? "java.lang.String")))
  (is (false? (class-available? "java.lang.MicrosoftRocks")))
  (is (class-available? "java.lang.String"))
)
  
(deftest test-describe-class
  (is (= {:name "java.lang.String", :final true} (untyped-describe-class String)))
  (is (= {:name "java.lang.String", :final true} (typed-describe-class String)))
  (is (thrown? IllegalArgumentException (untyped-describe-class "foo")))
  (is (thrown? ClassCastException (typed-describe-class "foo")))
)
	 


  

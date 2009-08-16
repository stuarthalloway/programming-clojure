(ns examples.test.introduction
  (:use clojure.contrib.test-is)
  (:use examples.introduction))

(deftest test-blank?
  (is (blank? nil))
  (is (blank? ""))
  (is (blank? " "))
  (is (false? (blank? "boo")))
)

(deftest test-accounts
  (dosync (commute accounts conj (struct account "CLSS" 0)))
  (is (= #{{:id "CLSS" :balance 0}} @accounts))
)

(deftest test-fibs
  (is (= [0 1 1 2 3 5 8 13 21 34] (take 10 fibs))))

(deftest test-hello-with-memory
  (dosync (ref-set visitors #{}))
  (is (= (hello-with-memory "Muness") "Hello, Muness" ))
  (is (= (hello-with-memory "Muness") "Welcome back, Muness" ))
  (is (= #{"Muness"} @visitors))
)

(deftest test-hello-docstring
  (is (= (with-out-str (hello-docstring "Aaron")) "Hello, Aaron\n"))
)

; multiple hellos in this chapter. Last one should have a docstring
(deftest test-hello-has-a-docstring
  (is (= "Returns a hello message, calling you by username.\n  Knows if you have been here before."
         (:doc ^#'hello))))

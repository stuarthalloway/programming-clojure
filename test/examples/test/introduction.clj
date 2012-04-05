(ns examples.test.introduction
  (:use clojure.test
        examples.introduction))

(deftest test-blank?
  (is (blank? nil))
  (is (blank? ""))
  (is (blank? " "))
  (is (false? (blank? "boo"))))

(deftest test-accounts
  (dosync (commute accounts conj (struct account "CLSS" 0)))
  (is (= #{{:id "CLSS" :balance 0}} @accounts)))

(deftest test-fibs
  (is (= [0 1 1 2 3 5 8 13 21 34] (take 10 fibs))))

(deftest test-hello-docstring
  (is (= (with-out-str (hello-docstring "Aaron")) "Hello, Aaron\n")))

                                        ; multiple hellos in this chapter. Last one should have a docstring
(deftest test-hello-has-a-docstring
  (is (= "Writes hello message to *out*. Calls you by username.\n  Knows if you have been here before."
         (:doc (meta #'hello)))))

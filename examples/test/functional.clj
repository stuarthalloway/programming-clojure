(ns examples.test.functional
  (:use clojure.contrib.test-is 
        examples.functional
        [clojure.contrib.seq-utils :only (indexed)]))

(def ten-fibs [0 1 1 2 3 5 8 13 21 34])

(deftest test-stack-crushing-fibo
  (is (= ten-fibs (map stack-consuming-fibo (range 0 10))))
  (is (thrown? StackOverflowError (stack-consuming-fibo 1000000))))

(deftest test-tail-recursive-fibo
  (is (= ten-fibs (map tail-recursive-fibo (range 0 10))))
  (is (thrown? StackOverflowError (tail-recursive-fibo 1000000))))

(deftest test-loop-recur-fibo
  (is (= ten-fibs (map loop-recur-fibo (range 0 10)))))

(deftest test-fibo-series
  (is (= ten-fibs (fibo-series 10))))

(deftest test-lazy-cons-fibo
  (is (= ten-fibs (take 10 (lazy-cons-fibo)))))

(deftest test-head-fibo 
  (is (= ten-fibs (take 10 head-fibo))))
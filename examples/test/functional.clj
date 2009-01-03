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

(deftest test-symbol-replace
  (are (= _1 _2)
       () (symbol-replace () 'a 'b)
       '(a) (symbol-replace '(a) 'b 'c)
       '(c) (symbol-replace '(b) 'b 'c)
       '(a (d e)) (symbol-replace '(a (d e)) 'b 'c)
       '(c (c c)) (symbol-replace '(b (b b)) 'b 'c)
))

(deftest test-hofstadter-m-f
  (are (= _1 _2)
       [0, 0, 1, 2, 2, 3, 4, 4, 5, 6, 6, 7, 7, 8, 9, 9, 10, 11, 11, 12, 12]
       (map h-male (range 0 21))
       [1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6, 7, 8, 8, 9, 9, 10, 11, 11, 12, 13]
       (map h-female (range 0 21))))

(deftest test-count-heads-pairs
  (doseq [count-fn [count-heads-loop count-heads-by-pairs]]
      (are (= _1 _2)
	   0 (count-fn [:h :t])
	   1 (count-fn [:t :h :h :t])
	   2 (count-fn [:h :h :h])
)))

(ns examples.test.functional
  (:use clojure.test 
        examples.functional))

(def ten-fibs [0 1 1 2 3 5 8 13 21 34])

(deftest test-stack-crushing-fibo
  (is (= ten-fibs (map stack-consuming-fibo (range 0 10))))
  (is (thrown? StackOverflowError (stack-consuming-fibo 1000000N))))

(deftest test-tail-fibo
  (is (= ten-fibs (map tail-fibo (range 0 10))))
  (is (thrown? StackOverflowError (tail-fibo 1000000N))))

(deftest test-recur-fibo
  (is (= ten-fibs (map recur-fibo (range 0 10)))))

(deftest test-fibo
  (is (= ten-fibs (take 10 (fibo)))))

(deftest test-head-fibo 
  (is (= ten-fibs (take 10 head-fibo))))

(deftest test-faux-curry
  (is (fn? (faux-curry + 1)))
  (is (fn? ((faux-curry + 1) 1)))
  (is (= 2 (((faux-curry + 1) 1)))))

(deftest test-count-heads-pairs
  (doseq [count-fn [count-heads-loop count-heads-by-pairs count-heads-by-runs]]
      (are [x y] (= x y)
	   0 (count-fn [:h :t])
	   1 (count-fn [:t :h :h :t])
	   2 (count-fn [:h :h :h])
)))

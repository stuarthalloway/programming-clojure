(ns examples.test.trampoline
  (:use clojure.test examples.trampoline))

(def ten-fibs [0 1 1 2 3 5 8 13 21 34])

(deftest test-tail-fibo
  (is (= ten-fibs 
	 (map (partial trampoline trampoline-fibo) (range 0 10)))))

(deftest test-my-odd
  (is (= [false true false true false]
	 (map (partial trampoline my-odd?) (range 5)))))

(deftest test-my-even
  (is (= [true false true false true]
	 (map (partial trampoline my-even?) (range 5)))))

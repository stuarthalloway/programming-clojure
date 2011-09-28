(ns examples.test.male-female-seq
  (:use clojure.test 
        examples.male-female-seq))

(deftest test-hofstadter-m-f
  (are [x y] (= x y)
       [0, 0, 1, 2, 2, 3, 4, 4, 5, 6, 6, 7, 7, 8, 9, 9, 10, 11, 11, 12, 12]
       (take 21 m-seq)
       [1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6, 7, 8, 8, 9, 9, 10, 11, 11, 12, 13]
       (take 21 f-seq)))


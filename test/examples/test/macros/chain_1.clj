(ns examples.test.macros.chain-1
  (:use clojure.test examples.macros.chain-1))

(deftest test-chain-1
  (are [x y] (= x y)
   (macroexpand-1 '(examples.macros.chain-1/chain a b)) '(. a b))
  (is (thrown? IllegalArgumentException (macroexpand-1 '(examples.macros.chain-1/chain a b c)))))

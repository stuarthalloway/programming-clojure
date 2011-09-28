(ns examples.test.macros.chain-5
  (:use clojure.test examples.macros.chain-5))

(deftest test-chain-5
  (are [x y] (= x y)
   (macroexpand-1 '(examples.macros.chain-5/chain a b)) '(. a b)
   (macroexpand-1 '(examples.macros.chain-5/chain a b c)) '(examples.macros.chain-5/chain (. a b) c)))

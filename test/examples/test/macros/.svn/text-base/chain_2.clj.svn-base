(ns examples.test.macros.chain-2
  (:use clojure.test examples.macros.chain-2))

(deftest test-chain-2
  (are [x y] (= x y)
   (macroexpand-1 '(examples.macros.chain-2/chain a b)) '(. a b)
   (macroexpand-1 '(examples.macros.chain-2/chain a b c)) '(chain (. a b) c)))

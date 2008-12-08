(ns examples.test.macros.chain-4
  (:use clojure.contrib.test-is examples.macros.chain-4))

(deftest test-chain-4
  (are =
   (macroexpand-1 '(examples.macros.chain-4/chain a b)) '(. a b)
   (macroexpand-1 '(examples.macros.chain-4/chain a b c)) '(examples.macros.chain-4/chain (. a b) (c))
))
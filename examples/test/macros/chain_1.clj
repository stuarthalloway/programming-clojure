(ns examples.test.macros.chain-1
  (:use clojure.contrib.test-is examples.macros.chain-1))

(deftest test-chain-1
  (each=
   (macroexpand-1 '(examples.macros.chain-1/chain a b)) '(. a b))
  (throws IllegalArgumentException (macroexpand-1 '(examples.macros.chain-1/chain a b c)))
)

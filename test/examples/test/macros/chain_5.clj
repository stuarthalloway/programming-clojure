(ns examples.test.macros.chain-5
  (:use clojure.contrib.test-is examples.macros.chain-5))

(deftest test-chain-5
  (are (= _1 _2)
   (macroexpand-1 '(examples.macros.chain-5/chain a b)) '(. a b)
   (macroexpand-1 '(examples.macros.chain-5/chain a b c)) '(examples.macros.chain-5/chain (. a b) c)
))
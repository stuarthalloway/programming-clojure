(ns examples.test.macros.chain-3
  (:use clojure.contrib.test-is examples.macros.chain-3))

(deftest test-chain-3
  (are (= _1 _2)
   (macroexpand-1 '(examples.macros.chain-3/chain a b)) '(. a b))
  (is (thrown? IllegalArgumentException (macroexpand-1 '(examples.macros.chain-3/chain a b c))))
)

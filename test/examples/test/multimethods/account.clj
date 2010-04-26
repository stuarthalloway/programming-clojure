(ns examples.test.multimethods.account
  (:use clojure.contrib.test-is)
  (:use examples.multimethods.account))

(alias 'acc 'examples.multimethods.account)

(deftest test-interest-rate
  (are (= _1 _2)
   (interest-rate {:tag ::acc/Checking}) 0M
   (interest-rate {:tag ::acc/Savings}) 0.05M
))

(deftest test-account-level
  (are (= _1 _2)
   (account-level {:tag ::acc/Checking, :balance 4999}) ::acc/Basic
   (account-level {:tag ::acc/Checking, :balance 5000}) ::acc/Premium
   (account-level {:tag ::acc/Savings, :balance 999}) ::acc/Basic
   (account-level {:tag ::acc/Savings, :balance 1000}) ::acc/Premium
))

  
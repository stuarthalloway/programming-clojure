(ns examples.test.multimethods.service-charge-2
  (:use clojure.test examples.multimethods.service-charge-2 examples.multimethods.account))

(alias 'acc 'examples.multimethods.account)

(deftest test-service-charge
  (are [x y] (= x y)
   (service-charge {:tag ::acc/Checking, :balance 4999}) 25
   (service-charge {:tag ::acc/Checking, :balance 5000}) 0
   (service-charge {:tag ::acc/Savings, :balance 999}) 10
   (service-charge {:tag ::acc/Savings, :balance 1000}) 0))

  

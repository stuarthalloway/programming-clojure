(ns examples.multimethods.service-charge-2
  (:require examples.multimethods.account))

(in-ns 'examples.multimethods.account)
(clojure.core/use 'clojure.core)

; START:service-charge
(defmulti service-charge (fn [acct] [(account-level acct) (:tag acct)]))
(defmethod service-charge [::Basic ::Checking]   [_] 25)
(defmethod service-charge [::Basic ::Savings]    [_] 10)
(defmethod service-charge [::Premium ::Checking] [_] 0)
(defmethod service-charge [::Premium ::Savings]  [_] 0)
; END:service-charge

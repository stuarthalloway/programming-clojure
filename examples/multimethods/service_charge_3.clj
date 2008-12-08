(ns examples.multimethods.service-charge-3
  (:require examples.multimethods.account))

(in-ns 'examples.multimethods.account)
(clojure.core/use 'clojure.core)

; START: derive
(derive ::Savings ::Account)
(derive ::Checking ::Account)
; END: derive

; START: service-charge
(defmulti service-charge (fn [acct] [(account-level acct) (:tag acct)]))
(defmethod service-charge [::Basic ::Checking]   [_] 25)
(defmethod service-charge [::Basic ::Savings]    [_] 10)
(defmethod service-charge [::Premium ::Account] [_] 0)
; END: service-charge


  
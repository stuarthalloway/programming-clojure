(ns examples.multimethods.service-charge-1
  (:require examples.multimethods.account))

; namespace hackery for segregating multiple service-charge impls
(in-ns 'examples.multimethods.account)
(clojure.core/use 'clojure.core)

; START:service-charge
; bad approach
(defmulti service-charge account-level)
(defmethod service-charge ::Basic [acct]
  (if (= (:tag acct) ::Checking) 25 10))
(defmethod service-charge ::Premium [_] 0)
; END:service-charge


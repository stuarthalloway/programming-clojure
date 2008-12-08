; START:account
(ns examples.multimethods.account)

(defstruct account :id :tag :balance)
; END:account

; START:interest-rate
(defmulti interest-rate :tag)
(defmethod interest-rate ::Checking [_] 0M)
(defmethod interest-rate ::Savings [_] 0.05M)
; END:interest-rate  

; START:account-level
(defmulti account-level :tag)
(defmethod account-level ::Checking [acct]
  (if (>= (:balance acct) 5000) ::Premium ::Basic))
(defmethod account-level ::Savings [acct]
  (if (>= (:balance acct) 1000) ::Premium ::Basic))
; END:account-level


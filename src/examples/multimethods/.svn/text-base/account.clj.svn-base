; START:account
(ns examples.multimethods.account)

(defstruct account :id :tag :balance)
; END:account

(alias 'acc 'examples.multimethods.account)

; START:interest-rate
(defmulti interest-rate :tag)
(defmethod interest-rate ::acc/Checking [_] 0M)
(defmethod interest-rate ::acc/Savings [_] 0.05M)
; END:interest-rate  

; START:account-level
(defmulti account-level :tag)
(defmethod account-level ::acc/Checking [acct]
  (if (>= (:balance acct) 5000) ::acc/Premium ::acc/Basic))
(defmethod account-level ::acc/Savings [acct]
  (if (>= (:balance acct) 1000) ::acc/Premium ::acc/Basic))
; END:account-level


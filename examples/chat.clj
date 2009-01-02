(ns examples.chat)

; START: message	      
(defstruct message :sender :text)
; END: message

; START: messages
(def messages (ref ()))
; END: messages      

; START: validate-message-list
(defn validate-message-list [lst]
  (if (not-every? #(and (:sender %) (:text %)) lst)
    (throw (IllegalStateException. "Not a valid message"))
    true))

(def messages (ref () :validator validate-message-list))
; END: validate-message-list

; START: naive-add-message
; bad idea
(defn naive-add-message [msg]
  (dosync (ref-set messages (cons msg @messages))))
; END: naive-add-message
 
; START: add-message
(defn add-message [msg]
  (dosync (commute messages conj msg)))
; END: add-message


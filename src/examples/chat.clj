(ns examples.chat)

; START: message
(defrecord Message [sender text])
; END: message

; START: messages
(def messages (ref ()))
; END: messages      

; START: validate-message-list
(def validate-message-list
  (partial every? #(and (:sender %) (:text %))))

(def messages (ref () :validator validate-message-list))
; END: validate-message-list

; START: naive-add-message
; bad idea
(defn naive-add-message [msg]
  (dosync (ref-set messages (cons msg @messages))))
; END: naive-add-message
 
; START: add-message
(defn add-message [msg]
  (dosync (alter messages conj msg)))
; END: add-message

; START: add-message-commute
(defn add-message-commute [msg]
  (dosync (commute messages conj msg)))
; END: add-message-commute

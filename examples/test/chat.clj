(ns examples.test.chat
  (:use clojure.contrib.test-is)
  (:require examples.chat))

(alias 'c 'examples.chat)

(deftest naive-add-message
  (dosync (ref-set c/messages ()))
  (c/naive-add-message (struct c/message "jdoe" "hello"))
  (is (= '({:sender "jdoe" :text "hello"}) @c/messages))
)

(deftest add-message
  (dosync (ref-set c/messages ()))
  (c/add-message (struct c/message "jdoe" "goodbye"))
  (is (= '({:sender "jdoe" :text "goodbye"}) @c/messages))
)

(deftest add-message-commute
  (dosync (ref-set c/messages ()))
  (c/add-message-commute (struct c/message "jdoe" "goodbye"))
  (is (= '({:sender "jdoe" :text "goodbye"}) @c/messages))
)

(deftest validate-message-list
  (is (true? (c/validate-message-list ())))
  (is (true? (c/validate-message-list '({:sender "X" :text "Y"}))))
  (is (nil? (c/validate-message-list '({}))))
)

  
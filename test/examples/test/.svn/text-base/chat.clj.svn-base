(ns examples.test.chat
  (:use clojure.test)
  (:require [examples.chat :as c]))

(deftest naive-add-message
  (dosync (ref-set c/messages ()))
  (c/naive-add-message (c/->Message "jdoe" "hello"))
  (is (= [#examples.chat.Message{:sender "jdoe" :text "hello"}]
         @c/messages)))

(deftest add-message
  (dosync (ref-set c/messages ()))
  (c/add-message (c/->Message "jdoe" "goodbye"))
  (is (= [#examples.chat.Message{:sender "jdoe" :text "goodbye"}]
         @c/messages)))

(deftest add-message-commute
  (dosync (ref-set c/messages ()))
  (c/add-message-commute (c/->Message "jdoe" "goodbye"))
  (is (= [#examples.chat.Message{:sender "jdoe" :text "goodbye"}]
         @c/messages)))

(deftest validate-message-list
  (is (true? (c/validate-message-list ())))
  (is (true? (c/validate-message-list '({:sender "X" :text "Y"}))))
  (is (false? (c/validate-message-list '({})))))

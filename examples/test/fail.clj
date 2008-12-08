(ns examples.test.fail
    (:use examples.index-of-any clojure.contrib.test-is))

; START:test-is-failure
(deftest test-that-demonstrates-failure
  (is (nil? (index-of-any "aba" #{\a})))) 
; END:test-is-failure

; START:test-is-error-message
(deftest test-that-demonstrates-error-message
  (is (nil? (index-of-any "aba" #{\a})) "your message here"))
; END:test-is-error-message

(run-tests)
(ns examples.test.fail
    (:use examples.index-of-any clojure.contrib.test-is))

; START:test-is-failure
(deftest test-that-demonstrates-failure
  (is (= 5 (+ 2 2))))
; END:test-is-failure

; START:test-is-error-message
(deftest test-that-demonstrates-error-message
  (is (= 3 Math/PI) "PI is an integer!?"))
; END:test-is-error-message

(run-tests)
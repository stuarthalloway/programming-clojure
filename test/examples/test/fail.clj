(ns examples.test.fail
  (:use examples.index-of-any clojure.test))

; START:test-failure
(deftest test-that-demonstrates-failure
  (is (= 5 (+ 2 2))))
; END:test-failure

; START:test-error-message
(deftest test-that-demonstrates-error-message
  (is (= 3 Math/PI) "PI is an integer!?"))
; END:test-error-message

(run-tests)

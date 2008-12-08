(ns lancet.test.defrunonce-1
  (:use clojure.contrib.test-is))

; this test depends on a proposed enhancement to test-is...
; and is broken anyway
;; (deftest test-defrunonce-1-refers-to-nonexistent-vars
;;   (are =
;;    (.getMessage (throws Exception (use :reload 'lancet.defrunonce-1)))
;;    "java.lang.Exception: Unable to resolve symbol: reset-fn in this context (defrunonce_1.clj:6)"))

(deftest test-defrunonce-1-refers-to-nonexistent-vars
  (is (thrown? Exception (use :reload 'lancet.defrunonce-1))))
  
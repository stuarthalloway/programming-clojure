; START:test
(ns examples.test.index-of-any
  (:use examples.index-of-any clojure.test))

(deftest test-index-of-any-with-nil-args
  (is (nil? (index-of-any #{\a} nil)))
  (is (nil? (index-of-any nil "foo"))))

(deftest test-index-of-any-with-empty-args
  (is (nil? (index-of-any #{\a} "")))
  (is (nil? (index-of-any #{} "foo"))))

(deftest test-index-of-any-with-match
  (is (zero? (index-of-any #{\z \a} "zzabyycdxx")))
  (is (= 3 (index-of-any #{\b \y} "zzabyycdxx"))))

(deftest test-index-of-any-without-match
  (is (nil? (index-of-any #{\z} "aba"))))
; END:test



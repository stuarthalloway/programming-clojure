; START:test-is
(ns examples.test.index-of-any
    (:use examples.index-of-any clojure.contrib.test-is))

(deftest test-index-of-any-with-nil-args
  (is (nil? (index-of-any nil #{\a})))
  (is (nil? (index-of-any "foo" nil))))

(deftest test-index-of-any-with-empty-args
  (is (nil? (index-of-any "" #{\a})))
  (is (nil? (index-of-any "foo" #{}))))

(deftest test-index-of-any-with-match
  (is (zero? (index-of-any "zzabyycdxx" #{\z \a})))
  (is (= 3 (index-of-any "zzabyycdxx" #{\b \y}))))

(deftest test-index-of-any-without-match
  (is (nil? (index-of-any "aba" #{\z}))))
; END:test-is



(ns examples.test.exploring
  (:use clojure.test examples.exploring))

(defn call-date [& args]
  (with-out-str (apply date args)))

(deftest test-date
  (is (= "a and b went out with 2 chaperones.\n"
         (call-date "a" "b" "c" "d"))))

(deftest test-is-small-with-if?
  (is (= "yes" (is-small-with-if? 99)))
  (is (= nil (is-small-with-if? 100)))
  (is (= nil (is-small-with-if? 101))))

(deftest test-is-small-with-else?
  (is (= "yes" (is-small-with-else? 99)))
  (is (= "no" (is-small-with-else? 100)))
  (is (= "no" (is-small-with-else? 101))))

(deftest test-is-small-with-do?
  (is (=
       "Saw a big number 100\nSaw a big number 101\n"
       (with-out-str
         (is (= "yes" (is-small-with-do? 99)))
         (is (= "no" (is-small-with-do? 100)))
         (is (= "no" (is-small-with-do? 101)))))))

(deftest test-demo-loop
  (is (= [5 4 3 2 1] (demo-loop))))

(deftest test-countdown
  (is (= [4 3 2 1] (countdown [] 4))))

(deftest test-index-of-any
  (is (= nil (index-of-any #{\d \e \f \g} "abc")))
  (is (= 1 (index-of-any #{\d \e \f \g} "add"))))

(deftest test-greeting
  (is (= "Hello, foo" (simple-greeting "foo"))))

(deftest test-greeting-with-default
  (is (= "Hello, world" (greeting-with-default)))
  (is (= "Hello, foo" (greeting-with-default "foo"))))

(deftest test-indexable-word
  (is (indexable-word? "super"))
  (is (false? (indexable-word? "at"))))

(deftest test-indexable-words
  (is (= ["this" "working"] (indexable-words "this is working"))))

(deftest test-make-greeter
  (let [g (make-greeter "howdy")]
    (is (= "howdy, podner" (g "podner")))))

(deftest test-square-corners
  (is (= [[0 0] [2 0] [2 2] [0 2]]
           (square-corners 0 0 2))))

(deftest test-busted
  (is (thrown? AssertionError (test #'busted))))

(deftest test-greet-author-1
  (is (= "Hello, John\n" (with-out-str (greet-author-1 {:first-name "John"})))))

(deftest test-greet-author-2
  (is (= "Hello, John\n" (with-out-str (greet-author-2 {:first-name "John"})))))

(deftest test-ellipsize
  (is (= "This had better ..."
         (ellipsize "This had better work!")))
  (is (= "This had better ..."
         (ellipsize "This     had     better    work     too!"))))

; START: thrown
(deftest test-divide-by-zero
  (is (thrown? ArithmeticException (/ 5 0))))
; END: thrown

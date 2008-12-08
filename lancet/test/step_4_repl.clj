(ns lancet.test.step-4-repl
  (:use clojure.contrib.test-is)
  (:use lancet.step-4-repl))

;; Predicates

(deftest test-has-run-fn
  (let [x (with-meta [] {:has-run (fn [] :boo)})]
    (is (= :boo (has-run-fn x)))))

(deftest test-has-run?
  (def #^{:has-run (fn [] :bang)} fn#)
  (is (= :bang (has-run? fn#))))

(deftest test-reset
  (def #^{:reset-fn (fn [] :zap)} fn#)
  (is (= :zap (reset fn#))))

;; (deftest test-runonce
;;   (defrunonce fn# "docstring" (print "foo") :result)
;;   (is (= "foo" (with-out-str (fn#)))))

  
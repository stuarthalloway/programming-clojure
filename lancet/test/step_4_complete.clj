(ns lancet.test.step-4-complete
  (:use clojure.contrib.test-is [clojure.set :only (intersection)])
  (:use lancet.step-4-complete))

;; Predicates

(deftest test-has-run?
  (def #^{:has-run (fn [] :bang)} fn#)
  (is (= :bang (has-run? fn#))))

(deftest test-reset
  (def #^{:reset-fn (fn [] :zap)} fn#)
  (is (= :zap (reset fn#))))

(deftest test-task-names
  (let [some-names #{'echo 'mkdir}]
    (are =
	 (intersection (into #{} (task-names)) some-names) some-names)))

(deftest test-safe-ant-name
  (are =
       (safe-ant-name 'echo) 'echo
       (safe-ant-name 'import) 'ant-import))

(deftest test-define-all-ant-tasks-defines-echo
  (let [echo-task (echo {:description "foo"})]
    (are =
	 (.getDescription echo-task) "foo"
	 (class echo-task) org.apache.tools.ant.taskdefs.Echo)))
  
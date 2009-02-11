(ns lancet.test.step-2-repl
    (:import (java.util.logging Level Logger))
    (:use clojure.contrib.test-is lancet.step-2-repl))

; not general purpose!
(defn get-field [obj field]
  (let [fld (.getDeclaredField (class obj) field)]
    (.setAccessible fld true)
    (.get fld obj)))

(deftest test-ant-project
  (let [listeners (.getBuildListeners ant-project)]
    (is (=
	  (count (filter #(= (class %) org.apache.tools.ant.NoBannerLogger) listeners))
	  1))))

(deftest test-instantiate-task
  (let [echo-task (instantiate-task ant-project "echo" {:message "foo"})]
    (is (= (get-field echo-task "message") "foo")))
  (is (thrown? IllegalArgumentException (instantiate-task ant-project "not-a-task-name")))
)

; using Logger as an example bean. Amazing how few built-in Java
; classes are beans...
(deftest test-property-setters
  (let [bean (Logger/getAnonymousLogger)]
    (is (nil? (.getLevel bean)))
    (set-property! bean :level Level/SEVERE)
    (is (= (.getLevel bean) Level/SEVERE))
    (set-properties! bean {:level Level/INFO})
    (is (= (.getLevel bean) Level/INFO))))
  
(deftest test-property-descriptor
  (let [bean (Logger/getAnonymousLogger)]
    (is (nil? (property-descriptor bean :foobar)))
    (is (not (nil? (property-descriptor bean :level)))))) 




  
(ns lancet.test.step-1-complete
  (:use clojure.contrib.test-is)
  (:use lancet.step-1-complete))

(deftest test-ant-project
  (let [listeners (.getBuildListeners ant-project)]
    (is (=
	 (count (filter #(= (class %) org.apache.tools.ant.NoBannerLogger) listeners))
	 1))))

(deftest test-instantiate-task
  (is (= 
       (class (instantiate-task ant-project "echo"))
       org.apache.tools.ant.taskdefs.Echo))
  (is (thrown? IllegalArgumentException (instantiate-task ant-project "not-a-task-name"))))

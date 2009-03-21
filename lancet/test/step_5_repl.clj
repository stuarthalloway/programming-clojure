(ns lancet.test.step-5-repl
    (:import (java.util.logging Level Logger) (java.io File))
    (:use clojure.contrib.test-is lancet.step-5-repl))

; not general purpose!
(defn get-field [obj field]
  (let [fld (.getDeclaredField (class obj) field)]
    (.setAccessible fld true)
    (.get fld obj)))

(deftest test-instantiate-task
  (let [mkdir-task (instantiate-task ant-project "mkdir" {:dir "output/step-5-repl"})]
    (is (= (get-field mkdir-task "dir") (File. "output/step-5-repl"))))
)





  
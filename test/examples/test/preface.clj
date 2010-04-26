(ns examples.test.preface
  (:use clojure.contrib.test-is))

(deftest test-load-preface
  (is (= "hello\n" 
	 (with-out-str (use :reload 'examples.preface))))
)



  
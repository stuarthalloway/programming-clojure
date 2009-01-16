(ns lancet.test.deftarget-1
  (:use clojure.contrib.test-is))

(deftest test-deftarget-1-refers-to-nonexistent-vars
  (let [e (is (thrown? Exception (use :reload 'lancet.deftarget-1)))
	msg-prefix "java.lang.Exception: Unable to resolve symbol: "]
    (is
     (= 
      (.substring (.getMessage e) 0 (count msg-prefix))
      msg-prefix))))


  
  
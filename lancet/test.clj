(ns lancet.test
  (:use clojure.contrib.test-is))

(def tests [:step-1-repl :step-1-complete
	    :step-2-repl :step-2-complete
	    :step-3-repl :step-3-complete
	    :step-4-repl :step-4-complete
	    :defrunonce-1])

(defn test-name
  [test]
  (symbol (str "lancet.test." (name test))))

(doseq [test tests]
  (require (test-name test)))

(doseq [test tests]
  (println "\n\n=====>" test)
  (run-tests (test-name test)))

(shutdown-agents)

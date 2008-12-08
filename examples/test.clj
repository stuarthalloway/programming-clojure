(ns exmaples.test
  (:use clojure.contrib.test-is))

(def tests [:chat :exploring :interop :introduction :multimethods :preface
	    :multimethods.account :multimethods.service-charge-1
	    :multimethods.service-charge-2 :multimethods.service-charge-3
	    :sequences :index-of-any :life-without-multi :multimethods.default
	    :macros :macros.chain-1 :macros.chain-2 :macros.chain-3 :macros.chain-4
	    :macros.chain-5 :lazy-index-of-any :macros.bench-1
	    :concurrency])

(defn test-name
  [test]
  (symbol (str "examples.test." (name test))))

(doseq [test tests]
  (require (test-name test)))

(doseq [test tests]
  (println "\n\n=====>" test)
  (run-tests (test-name test)))

; TODO: document in book
(shutdown-agents)

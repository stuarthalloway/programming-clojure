(ns examples.test.snippet
    (:use clojure.contrib.test-is clojure.contrib.sql examples.snippet))

(deftest insert-and-select-some-snippets
  (let [stub-time (now)]
    (binding [now (fn [] stub-time)]
      (sample-snippets)
      (is (= [{:id 0, :body "(println :boo)", :created_at stub-time}
	      {:id 1, :body "(defn foo [] 1)", :created_at stub-time}]
	     (select-snippets)))
      (insert-snippet "boo!")
      (is (= {:id 2, :body "boo!", :created_at stub-time}
	     (select-snippet 2))))))

(deftest drop-and-create-snippets-table
  (with-connection db (drop-snippets))
  (is (= "Table not found in statement [select * from snippets]"
	 (.getMessage (is (thrown? java.sql.SQLException (select-snippets))))))
  (ensure-snippets-table-exists)
  (is (= () (select-snippets))))






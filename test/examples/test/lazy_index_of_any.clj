(ns examples.test.lazy-index-of-any
    (:use examples.lazy-index-of-any clojure.test))

(deftest test-lazy-index-of-any-with-match
  (is (= (with-out-str (is (zero? (index-of-any #{\z \a} "zzabyycdxx"))))
	 "Iterating over z\n"))
  (is (= (with-out-str (is (= 3 (index-of-any #{\b \y} "zzabyycdxx"))))
	 "Iterating over z\nIterating over z\nIterating over a\nIterating over b\n")))




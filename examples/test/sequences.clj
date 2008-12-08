(ns examples.test.sequences
  (:import (java.io File))
  (:use clojure.contrib.test-is clojure.set examples.sequences))


(deftest test-demo-xml-seq
  (is (= (demo-xml-seq) ["J. S. Bach" "J. S. Bach" "W. A. Mozart"]))
)

(deftest test-clojure-loc
  (are =
   (non-svn? ".svn") false
   (non-svn? "foo") true
   (clojure-source? "foo.clj") true
   (clojure-source? "foo.java") false
   (non-blank? "  .  ") true
   (non-blank? " \t") false
))

(deftest test-recently-modified?
  (let [now #(System/currentTimeMillis)
	recent (proxy [File] ["recent"] (lastModified [] (now)))
	older (proxy [File] ["older"] (lastModified [] (- (now) (minutes 1000))))]
    (are =
     (recently-modified? recent) true
     (recently-modified? older) false
)))
		      
(deftest test-sets
  (are =
   (union languages beverages) #{"java" "c" "d" "clojure" "chai" "pop"}
   (difference languages beverages) #{"c" "d" "clojure"}
   (intersection languages beverages) #{"java"}
   (select #(= 1 (.length %)) languages) #{"c" "d"}
))

; TODO: add metadata to mark demo functions?
(deftest run-demos
  (demo-map-builders)
  (demo-merge-with)
  (demo-mutable-re)
)

  
(ns examples.test.sequences
  (:import java.io.File)
  (:use clojure.test clojure.set examples.sequences))


(deftest test-demo-xml-seq
  (is (= (demo-xml-seq) '("J. S. Bach" "F. Chopin" "W. A. Mozart"))))

(deftest test-clojure-loc
  (are [x y] (= x y)
   (non-svn? ".svn") false
   (non-svn? "/foo/bar/.svn") false
   (non-svn? ".svn/foo/bar") false
   (non-svn? "foo") true
   (clojure-source? "foo.clj") true
   (clojure-source? "foo.java") false
   (non-blank? "  .  ") true
   (non-blank? " \t") false))

(deftest test-recently-modified?
  (let [now #(System/currentTimeMillis)
	recent (proxy [File] ["recent"] (lastModified [] (now)))
	older (proxy [File] ["older"] (lastModified [] (- (now) (minutes-to-millis 1000))))]
    (are [x y] (= x y)
     (recently-modified? recent) true
     (recently-modified? older) false)))
		      
(deftest test-sets
  (are [x y] (= x y)
   (union languages beverages) #{"java" "c" "d" "clojure" "chai" "pop"}
   (difference languages beverages) #{"c" "d" "clojure"}
   (intersection languages beverages) #{"java"}
   (select #(= 1 (.length %)) languages) #{"c" "d"}))

(deftest test-joins
  (are [x y] (= x y)
   (join composers nations {:country :nation})
   #{{:language "German", :nation "Austria", :composer "W. A. Mozart", :country "Austria"} 
     {:language "German", :nation "Germany", :composer "J. S. Bach", :country "Germany"} 
     {:language "Italian", :nation "Italy", :composer "Giuseppe Verdi", :country "Italy"}}))

(deftest run-demos
  (demo-map-builders)
  (demo-merge-with)
  (demo-mutable-re))

  

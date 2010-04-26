(ns examples.index-of-any
    (:use clojure.contrib.seq-utils))

; START: index-of-any
(defn index-filter [pred coll]
  (when pred (for [[idx elt] (indexed coll) :when (pred elt)] idx)))  

(defn 
  #^{:test (fn []
	     (assert (nil? (index-of-any #{\a} nil)))
	     (assert (nil? (index-of-any #{\a} "")))
	     (assert (nil? (index-of-any nil "foo")))
	     (assert (nil? (index-of-any #{} "foo")))
	     (assert (zero? (index-of-any #{\z \a} "zzabyycdxx")))
	     (assert (= 3 (index-of-any #{\b \y} "zzabyycdxx")))
	     (assert (nil? (index-of-any #{\z} "aba"))))}
  index-of-any 
  [pred coll]
  (first (index-filter pred coll)))
; END: index-of-any



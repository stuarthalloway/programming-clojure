(ns examples.index-of-any
    (:use clojure.contrib.seq-utils))

(defn 
  #^{:test (fn []
	     (assert (nil? (index-of-any nil #{\a})))
	     (assert (nil? (index-of-any "" #{\a})))
	     (assert (nil? (index-of-any "foo" nil)))
	     (assert (nil? (index-of-any "foo" #{})))
	     (assert (zero? (index-of-any "zzabyycdxx" #{\z \a})))
	     (assert (= 3 (index-of-any "zzabyycdxx" #{\b \y})))
	     (assert (nil? (index-of-any "aba" #{\z}))))}
  index-of-any 
  [s chars]
  (some (fn [[idx char]] (if (get chars char) idx)) (indexed s)))


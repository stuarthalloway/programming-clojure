(ns examples.lazy-index-of-any)

(defn logging-seq [s]
  (if s
    (do (println "Iterating over" (first s))
	(lazy-cons (first s) (logging-seq (rest s))))))

(defn indexed [s] (map vector (iterate inc 0) s (logging-seq s)))
(defn index-filter [pred coll]
  (when pred (for [[idx elt] (indexed coll) :when (pred elt)] idx)))
(defn index-of-any [pred coll]
  (first (index-filter pred coll)))
		 

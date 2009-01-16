(ns examples.lazy-index-of-any)

(defn logging-seq [s]
  (if s
    (do (println "Iterating over" (first s))
	(lazy-cons (first s) (logging-seq (rest s))))))
		 
(defn indexed [s] (map vector (iterate inc 0) s (logging-seq s)))
(defn index-of-any [s chars]
  (some (fn [[idx char]] (if (get chars char) idx)) 
	(indexed s)))

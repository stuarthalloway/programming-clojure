(ns examples.symbol-replace)

; inspired by http://www.cs.uni.edu/~wallingf/patterns/recursion.html
(defn- coll-or-scalar [x & _] (if (coll? x) :collection :scalar))
(defmulti symbol-replace coll-or-scalar)

(defmethod symbol-replace :collection [coll oldsym newsym]
  (if (empty? coll)
    coll
    (cons (symbol-replace (first coll) oldsym newsym)
	  (symbol-replace (rest coll) oldsym newsym))))

(defmethod symbol-replace :scalar [obj oldsym newsym] 
  (if (= obj oldsym) newsym obj))

;; (defn symbol-replace [coll oldsym newsym]
;;   (if (empty? coll)
;;     coll
;;     (let [f (first coll)]
;;       (if (symbol? f)
;; 	(lazy-cons (if (= f oldsym) newsym f)
;; 		   (symbol-replace (rest coll) oldsym newsym))
;; 	(lazy-cons (symbol-replace f oldsym newsym)
;; 		   (symbol-replace (rest coll) oldsym newsym))))))



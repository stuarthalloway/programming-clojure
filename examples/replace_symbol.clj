(ns examples.replace-symbol)

; inspired by http://www.cs.uni.edu/~wallingf/patterns/recursion.html#3
; START: replace-symbol
(defn- coll-or-scalar [x & _] (if (coll? x) :collection :scalar))
(defmulti replace-symbol coll-or-scalar) ; <label id="code.replace-symbol.multi"/>

(defmethod replace-symbol :collection [coll oldsym newsym]
  (lazy-seq
   (when-let [s (seq coll)]
    (cons (replace-symbol (first coll) oldsym newsym) ; <label id="code.replace-symbol.lazy-cons"/>
	  (replace-symbol (rest coll) oldsym newsym)))))

(defmethod replace-symbol :scalar [obj oldsym newsym] 
  (if (= obj oldsym) newsym obj))
; END: replace-symbol

; START: deeply-nested
(defn deeply-nested [n]
  (loop [n n
	 result '(bottom)]
    (if (= n 0)
      result
      (recur (dec n) (list result)))))
; END: deeply-nested
   


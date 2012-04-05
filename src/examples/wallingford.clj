(ns examples.wallingford
  (:require [examples.replace-symbol :only (deeply-nested)]))

; based on http://www.cs.uni.edu/~wallingf/patterns/recursion.html#3
; START: replace-symbol
; overly-literal port, do not use
(declare replace-symbol replace-symbol-expression)
(defn replace-symbol [coll oldsym newsym]
  (if (empty? coll)
    ()
    (cons (replace-symbol-expression 
	   (first coll) oldsym newsym)
	  (replace-symbol 
	   (rest coll) oldsym newsym))))
(defn replace-symbol-expression [symbol-expr oldsym newsym]
  (if (symbol? symbol-expr)
    (if (= symbol-expr oldsym)
      newsym
      symbol-expr)
    (replace-symbol symbol-expr oldsym newsym)))
; END: replace-symbol
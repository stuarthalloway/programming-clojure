(ns examples.functional)

; START: stack-consuming-fibo
(defn stack-consuming-fibo [n]
  (cond
   (= n 0) 0 ; <label id="code.stack-consuming-fibo.0"/>
   (= n 1) 1 ; <label id="code.stack-consuming-fibo.1"/>
   true (+ (stack-consuming-fibo (- n 1)) (stack-consuming-fibo (- n 2))))) ; <label id="code.stack-consuming-fibo.n"/>
; END: stack-consuming-fibo

; START: tail-recursive-fibo
; beware: still consumes stack!
(defn tail-recursive-fibo [n]
  (cond
   (= n 0) 0
   (= n 1) 1
   true ((fn fib [f-2 f-1 current] ; <label id="code.tail-recursive-fibo.args"/>
	   (let [f (+ f-2 f-1)]
	     (if (= n current) ; <label id="code.tail-recursive-fibo.term"/>
	       f
	       (fib f-1 f (inc current))))) ; <label id="code.tail-recursive-fibo.recur"/>
	 0 1 2))) ; <label id="code.tail-recursive-fibo.basis"/>
; END: tail-recursive-fibo

; START: loop-recur-fibo    
(defn loop-recur-fibo [n]
  (cond
   (= n 0) 0
   (= n 1) 1
   true (loop [f-2 0, f-1 1, current 2] ; <label id="code.loop-recur-fibo.args"/>
	  (let [f (+ f-2 f-1)]
	    (if (= n current)
	      f
	      (recur f-1 f (inc current))))))) ; <label id="code.loop-recur-fibo.recur"/>
; END: loop-recur-fibo

; START: fibo-series
; returns series to n (heap-consuming!)
(defn fibo-series [count]
  (let [n (dec count)]
    (cond
     (= n 0) [0] ; <label id="code.fibo-series.0"/>
     (= n 1) [0 1] ; <label id="code.fibo-series.1"/>
     true (loop [series [0 1], current 2] ; <label id="code.fibo-series.series"/>
	    (let [f (+ (series (- current 1)) (series (- current 2)))]
	      (if (= current n)
		(conj series f) ; <label id="code.fibo-series.term"/>
		(recur (conj series f) (inc current)))))))) ; <label id="code.fibo-series.recur"/>
; END: fibo-series

; START: lazy-cons-fibo
(defn lazy-cons-fibo []
  ((fn fib [a b] ; <label id="code.lazy-cons.fib"/>
     (lazy-cons a (fib b (+ a b)))) ; <label id="code.lazy-cons.recur"/>
   0 1)) ; <label id="code.lazy-cons.basis"/>
; END: lazy-cons-fibo

; START: head-fibo
; holds the head (avoid!)
(def head-fibo (lazy-cat [0 1] (map + head-fibo (rest head-fibo))))
; END: head-fibo

; inspired by http://www.cs.uni.edu/~wallingf/patterns/recursion.html
(defn symbol-replace [coll oldsym newsym]
  (if (empty? coll)
    ()
    (let [f (first coll)]
      (if (symbol? f)
	(lazy-cons (if (= f oldsym) newsym f)
		   (symbol-replace (rest coll) oldsym newsym))
	(lazy-cons (symbol-replace f oldsym newsym)
		   (symbol-replace (rest coll) oldsym newsym))))))
      
(declare h-male h-female)
(defn h-male [n]
  (if (= n 0)
    0
    (- n (h-female (h-male (dec n))))))

(defn h-female [n]		 
  (if (= n 0)
    1
    (- n (h-male (h-female (dec n))))))
(def h-male (memoize h-male))
(def h-female (memoize h-female))

(def h-male-seq (map h-male (iterate inc 0)))
(def h-female-seq (map h-female (iterate inc 0)))

; START: count-heads-pairs
(defn count-heads-pairs [coll]
  (loop [cnt 0 coll coll] ; <label id="code.count-heads-loop.loop"/>
    (if (empty? coll) ; <label id="code.count-heads-loop.basis"/>
      cnt
      (recur (if (and (= :h (first coll)) (= :h (second coll))) ; <label id="code.count-heads-loop.filter"/>
	       (inc cnt)
	       cnt)
	     (rest coll)))))
; END: count-heads-pairs
(def count-heads-loop count-heads-pairs)

; START: by-pairs
(defn by-pairs [coll]
  (let [pair (take 2 coll)] ; <label id="code.by-pairs.take"/>
    (when (= 2 (count pair)) ; <label id="code.by-pairs.count"/>
      (lazy-cons pair (by-pairs (rest coll)))))) ; <label id="code.by-pairs.lazy"/>
; END: by-pairs

; START: count-heads-by-pairs
(defn count-heads-pairs [coll]
  (count (filter (fn [pair] (every? #(= :h %) pair)) 
		 (by-pairs coll))))
(def count-heads-by-pairs count-heads-pairs)
; START: count-heads-by-pairs

(def
#^{:doc "Count items matching a predicate."
   :arglists '([pred coll])}
count-if (comp count filter))

(defn
 count-runs
 "Count runs of length n where pred is true in coll."
 [n pred coll]
 (count-if #(every? pred %) (partition n 1 coll)))


(declare my-odd? my-even?)

(defn my-odd? [n]
  (if (= n 0)
    false
    (my-even? (dec n))))

(defn my-even? [n]
  (if (= n 0)
    true
    (my-odd? (dec n))))

(defn my-parity? [parity n]
  (if (= n 0)
    (= parity 0)
    (recur (- 1 parity) (dec n))))

(def my-even-2? (partial my-parity? 0))
(def my-odd-2? (partial my-parity? 1))

(defn machine [stream]
   (let [step {[:init 'c] :more
               [:more 'a] :more
               [:more 'd] :more
               [:more 'r] :end
               [:end nil] true}]
     (loop [state :init
            stream stream]
       (let [next (step [state (first stream)])]
         (when next
           (if (= next true)
               true
             (recur next (rest stream))))))))

  
(declare init more end)

(defn init [stream]
  (if (#{'c} (first stream))
    (more (rest stream))))

(defn more [stream]
  (cond 
   (#{'a 'd} (first stream)) (more (rest stream))
   (#{'r} (first stream)) (end (rest stream))))

(defn end [stream]
  (when-not (seq stream) true))



       

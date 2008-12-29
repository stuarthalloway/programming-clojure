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

(defn lazy-cons-fibo []
  ((fn fib [a b]
     (lazy-cons a (fib b (+ a b))))
   0 1))
    
(defn lazy-cat-fibo [] 
  (lazy-cat [0 1] (map + (lazy-cat-fibo) (rest (lazy-cat-fibo)))))

; holds the head (avoid!)
(def head-fibo (lazy-cat [0 1] (map + head-fibo (rest head-fibo))))


  


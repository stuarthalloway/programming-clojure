(ns examples.functional)

; START: stack-consuming-fibo
; bad idea
(defn stack-consuming-fibo [n]
  (cond
   (= n 0) 0 ; <label id="code.stack-consuming-fibo.0"/>
   (= n 1) 1 ; <label id="code.stack-consuming-fibo.1"/>
   :else (+ (stack-consuming-fibo (- n 1))    ; <label id="code.stack-consuming-fibo.n"/>
	    (stack-consuming-fibo (- n 2)))))     ; <label id="code.stack-consuming-fibo.n2"/>
; END: stack-consuming-fibo

; START: tail-fibo
(defn tail-fibo [n]
  (letfn [(fib ; <label id="code.tail-fibo.letfn"/>
	   [current next n] ; <label id="code.tail-fibo.args"/>
	   (if (zero? n)
	     current       ; <label id="code.tail-fibo.terminate"/>
	     (fib next (+ current next) (dec n))))] ; <label id="code.tail-fibo.recur"/>
    (fib 0N 1N n))) ; <label id="code.tail-fibo.call"/>
; END: tail-fibo

; START: recur-fibo    
; better but not great
(defn recur-fibo [n]
  (letfn [(fib 
            [current next n]
            (if (zero? n)
              current
              (recur next (+ current next) (dec n))))] ; <label id="code.recur-fibo.recur"/>
    (fib 0N 1N n)))
; END: recur-fibo

; START: lazy-seq-fibo
(defn lazy-seq-fibo 
  ([] 
     (concat [0 1] (lazy-seq-fibo 0N 1N))) ; <label id="code.lazy-seq-fibo.basis"/>
  ([a b]
     (let [n (+ a b)]                    ; <label id="code.lazy-seq-fibo.n"/>
       (lazy-seq                         ; <label id="code.lazy-seq-fibo.lazy-seq"/>
	(cons n (lazy-seq-fibo b n)))))) ; <label id="code.lazy-seq-fibo.cons"/>
; END: lazy-seq-fibo

; START: fibo
(defn fibo []
 (map first (iterate (fn [[a b]] [b (+ a b)]) [0N 1N])))
; END: fibo

; START: head-fibo
; holds the head (avoid!)
(def head-fibo (lazy-cat [0N 1N] (map + head-fibo (rest head-fibo))))
; END: head-fibo

; START: count-heads-pairs
(defn count-heads-pairs [coll]
  (loop [cnt 0 coll coll] ; <label id="code.count-heads-loop.loop"/>
    (if (empty? coll) ; <label id="code.count-heads-loop.basis"/>
      cnt
      (recur (if (= :h (first coll) (second coll)) ; <label id="code.count-heads-loop.filter"/>
	       (inc cnt)
	       cnt)
	     (rest coll)))))
; END: count-heads-pairs
(def count-heads-loop count-heads-pairs)

; START: by-pairs
; overly complex, better approaches follow...
(defn by-pairs [coll]
  (let [take-pair (fn [c]                           ; <label id="code.by-pairs.take"/>
		    (when (next c) (take 2 c)))]
    (lazy-seq                                       ; <label id="code.by-pairs.lazy-seq"/>
     (when-let [pair (seq (take-pair coll))]        ; <label id="code.by-pairs.when-let"/>
	 (cons pair (by-pairs (rest coll)))))))     ; <label id="code.by-pairs.cons"/>
; END: by-pairs

; START: count-heads-by-pairs
(defn count-heads-pairs [coll]
  (count (filter (fn [pair] (every? #(= :h %) pair)) 
		 (by-pairs coll))))
; END: count-heads-by-pairs
(def count-heads-by-pairs count-heads-pairs)

; START: count-if
(def ^{:doc "Count items matching a filter"}
  count-if (comp count filter))
; END: count-if

; START: count-runs
(defn count-runs
 "Count runs of length n where pred is true in coll."
 [n pred coll]
 (count-if #(every? pred %) (partition n 1 coll)))
; END: count-runs

; START: count-heads-by-runs
(def ^{:doc "Count runs of length two that are both heads"}
  count-heads-pairs (partial count-runs 2 #(= % :h)))
; END: count-heads-by-runs
(def count-heads-by-runs count-heads-pairs)

; START: my-odd-even
(declare my-odd? my-even?)

(defn my-odd? [n]
  (if (= n 0)
    false
    (my-even? (dec n))))

(defn my-even? [n]
  (if (= n 0)
    true
    (my-odd? (dec n))))
; END: my-odd-even

; START: parity
(defn parity [n]
  (loop [n n par 0]
    (if (= n 0)
      par
      (recur (dec n) (- 1 par)))))
; END: parity

; START: my-odd-even-parity
(defn my-even? [n] (= 0 (parity n)))
(defn my-odd? [n] (= 1 (parity n)))
; END: my-odd-even-parity

; START: curry
; almost a curry
(defn faux-curry [& args] (apply partial partial args))
; END: curry

; --------------------------------------------------------------------------------------
; -- See www.cs.brown.edu/~sk/Publications/Papers/Published/sk-automata-macros/paper.pdf
; --------------------------------------------------------------------------------------
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


	      

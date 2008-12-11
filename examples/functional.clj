(ns examples.functional)

(defn stack-consuming-fibo [n]
  (cond
   (= n 0) 0
   (= n 1) 1
   true (+ (stack-consuming-fibo (- n 1)) (stack-consuming-fibo (- n 2)))))

(defn tail-recursive-fibo [n]
  (cond
   (= n 0) 0
   (= n 1) 1
   true ((fn fib [n-2 n-1 item]
	   (if (= n item)
	     (+ n-2 n-1)
	     (fib n-1 (+ n-2 n-1) (inc item))))
	 0 1 2)))
    
(defn loop-recur-fibo [n]
  (cond
   (= n 0) 0
   (= n 1) 1
   true (loop [n-2 0 n-1 1 item 2]
	  (if (= n item)
	    (+ n-2 n-1)
	    (recur n-1 (+ n-2 n-1) (inc item))))))

; returns series to n (heap-consuming!)
(defn fibo-series [n]
  (loop [nums [0 1] last-pos 1]
    (if (>= last-pos n)
      (take n nums)
      (recur (conj nums (+ (nums last-pos) (nums (dec last-pos)))) 
	     (inc last-pos)))))

(defn lazy-cons-fibo []
  ((fn fib [a b]
     (lazy-cons a (fib b (+ a b))))
   0 1))
    
(defn lazy-cat-fibo [] 
  (lazy-cat [0 1] (map + (lazy-cat-fibo) (rest (lazy-cat-fibo)))))

; holds the head (avoid!)
(def head-fibo (lazy-cat [0 1] (map + head-fibo (rest head-fibo))))


  

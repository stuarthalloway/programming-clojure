(ns examples.trampoline)

; START: trampoline-fibo
; Example only. Don't write code like this.
(defn trampoline-fibo [n]
  (let [fib (fn fib [f-2 f-1 current]
	      (let [f (+ f-2 f-1)]
		(if (= n current) 
		  f
		  #(fib f-1 f (inc current)))))] ; <label id="code.trampoline.function"/>
  (cond
   (= n 0) 0
   (= n 1) 1
   :else (fib 0N 1 2))))
; END: trampoline-fibo

; START: odd-even
(declare my-odd? my-even?)

(defn my-odd? [n]
  (if (= n 0)
    false
    #(my-even? (dec n)))) ; <label id="code.trampoline.my-odd"/>

(defn my-even? [n]
  (if (= n 0)
    true
    #(my-odd? (dec n)))) ; <label id="code.trampoline.my-even"/>
; END: odd-even



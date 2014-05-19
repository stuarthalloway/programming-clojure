(ns examples.primes)

;; Taken from clojure.contrib.lazy-seqs
; primes cannot be written efficiently as a function, because
; it needs to look back on the whole sequence. contrast with
; fibs and powers-of-2 which only need a fixed buffer of 1 or 2
; previous values.
; For the origins of this version of the sieve, and the notion of a
; "wheel", see http://www.cs.hmc.edu/~oneill/papers/Sieve-JFP.pdf
; The "wheel" is a repeating sequence of offsets from one non-multiple
; to the next of the initial set of primes ([2 3 5 7] in this instance).
; The basis of the cycle is the set of integers from 1 to the lowest common
; multiple of the initial set of primes.
; The L.C.M in this case is 210. The cycle includes one integer
; (the last) that is the multiple of all the primes (the L.C.M.) and all
; integers less than the L.C.M. which are multiples of any other combination
; of the given primes.
; Only integers which are _not_ factored by these primes will be candidates as
; prime. The "wheel" gives the offset within a single L.C.M. cycle of the next
; candidate. It is a generalisation of the removal of multiples of 2 from the
; test for primes.
; The same offsets will apply to all subsequent cycles of 210 integers;
; hence wheel is defined as a cycle on the vector if candidate offsets.
(def primes
  (concat
    [2 3 5 7]
    (lazy-seq
      (let [primes-from
            (fn primes-from [n [f & r]]
              (if (some #(zero? (rem n %))
                        (take-while #(<= (* % %) n) primes))
                (recur (+ n f) r)
                (lazy-seq (cons n (primes-from (+ n f) r)))))
            wheel (cycle [2 4 2 4 6 2 6 4 2 4 6 6 2 6 4 2
                          6 4 6 8 4 2 4 2 4 8 6 4 6 2 4 6
                          2 6 6 4 2 4 6 2 6 4 2 4 2 10 2 10])]
        (primes-from 11 wheel)))))

;This version initially generates the 'wheel' from a vector of the initial primes.
;It makes a little more clear what the structure of the wheel is.
(def primes1
  (let [_1st-primes [2 3 5 7]
        non-multiples
        (fn [_1st-primes]
          (let [lcm (reduce * _1st-primes)
                ;; Return a cycle of booleans with multiples of n marked true
                mult-cycle (fn [n] (cycle (conj (vec (repeat (dec n) nil)) true)))
                conj-mult-cycles (fn [v n] (conj v (mult-cycle n)))]
            (->> _1st-primes
                 ;; vector of one mult-cycle for each initial prime
                 (reduce conj-mult-cycles [])
                 ;; a multiple (true) at any position in the cycle sets
                 ;; corresponding position true in the resulting lazy-seq
                 (apply map #(some identity %&))
                 ;; only need lowest common multiple plus 1.
                 ;; lcm + 1 is always a non-multiple
                 (take (inc lcm))
                 ;; convert multiples to nil and convert nil (non-multiple) to
                 ;; its own offset + 1; i.e to the actual non-multiple integer.
                 (map-indexed #(if (not %2) (inc %) nil))
                 ;; throw away the multiples
                 (filter identity)
                 )))
        make-offset-cycle
        (fn [_1st-primes]
          (let [indices (non-multiples _1st-primes)]
            ;; subtract in pairs to get the offset from each non-multiple
            ;; to the next in cycle
            (map #(- %2 %1) indices (rest indices))
            ))
        ]
    (concat
      _1st-primes
      (lazy-seq
        (let [primes-from
              (fn primes-from [n [f & r]]
                (if (some #(zero? (rem n %))
                          (take-while #(<= (* % %) n) primes1))
                  (recur (+ n f) r)
                  (lazy-seq (cons n (primes-from (+ n f) r)))))
              ]
          (primes-from
            (nth (non-multiples _1st-primes) 1)
            (drop 1 (cycle (make-offset-cycle _1st-primes)))))))))

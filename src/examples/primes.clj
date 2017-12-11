(ns examples.primes)

;; Taken from clojure.contrib.lazy-seqs
; primes cannot be written efficiently as a function, because
; it needs to look back on the whole sequence. contrast with
; fibs and powers-of-2 which only need a fixed buffer of 1 or 2
; previous values.
; For the origins of this version of the sieve, and the notion of a
; "wheel", see http://www.cs.hmc.edu/~oneill/papers/Sieve-JFP.pdf
; The "wheel" is a generalisation of the removal of multiples of 2 from the
; test for primes. It is a repeating sequence of offsets from one non-multiple
; to the next of the initial set of primes.
; The basis of the cycle is the set of integers from 1 to the lowest common
; multiple of the initial set of primes.
; For example, with an initial set of [2 3], the cycle length is (* 2 3) => 6.
; The non-multiples in the first set of 6 integers are [1 5]. These are the only
; candidates as prime. The following sets are simply incremented by the cycle
; length; so [1 5] [7 11] [13 17].  The increments are 1->5->7->11->13 etc.
; That is, 4, 2, 4, 2 etc, which is a repeating cycle of [4 2]. With a
; starting set of [2 3], the first candidate is 5, and the starting point of the
; increments cycle is 5->7; i.e. 2. So our repeating cycle becomes [2 4], rather
; than [4 2]. Note that the cycle of increments can be started at whatever point
; is required, and this applies to cycles of any length.
; The initial set in this case is [2 3 5 7]. The L.C.M is 210, and the increment
; cycle begins 1->11->13->17->19->23->29->31; i.e. 10, 2, 4, 2, 4, 6, 2.
; Because our starting point is always the first candidate following the last of
; the starting set, we always skip the first increment, which will recur at the
; beginning of the next cycle.  So the wheel starts [2 4 2 4 6 2...] and ends
; with the wrap-around to 10.
; The same offsets will apply to all subsequent cycles of 210 integers;
; hence wheel is defined as a cycle on the vector of candidate offsets.
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
                 ;; a multiple (true) at any position in the cycle sets the
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

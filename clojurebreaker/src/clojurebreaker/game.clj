(ns clojurebreaker.game
  (:use clojure.pprint)
  (:require [clojure.data :as data]
            [clojure.math.combinatorics :as comb]
            [clojure.java.io :as io]))

;; START:exact-matches
(defn exact-matches
  "Given two collections, return the number of positions where
   the collections contain equal items."
  [c1 c2]
  (let [[_ _ matches] (data/diff c1 c2)]
    (count (remove nil? matches))))
;; END:exact-matches

;; START:unordered-matches
(defn unordered-matches
  "Given two collections, return a map where each key is an item
   in both collections, and each value is the number of times the
   value occurs in the collection with fewest occurrences."
  [c1 c2]
  (let [f1 (select-keys (frequencies c1) c2)
        f2 (select-keys (frequencies c2) c1)]
    (merge-with min f1 f2)))
;; END:unordered-matches

;; START:score
(defn score
  [c1 c2]
  (let [exact (exact-matches c1 c2)
        unordered (apply + (vals (unordered-matches c1 c2)))]
    {:exact exact :unordered (- unordered exact)}))
;; END: score

;; START: generate-turn-inputs
(defn generate-turn-inputs
  "Generate all possible turn inputs for a clojurebreaker game
   with colors and n columns"
  [colors n]
  (-> (comb/selections colors n)
      (comb/selections 2)))
;; END: generate-turn-inputs

;; START: score-inputs
(defn score-inputs
  "Given a sequence of turn inputs, return a lazy sequence of
   maps with :secret, :guess, and :score."
  [inputs]
  (map
   (fn [[secret guess]]
     {:secret (seq secret)
      :guess (seq guess)
      :score (score secret guess)})
   inputs))
;; END: score-inputs

(->> (generate-turn-inputs [:r :g :b] 2)
 (score-inputs))

;; step 17 score-table
#_(score-all-games [:R :G :B] 3)

;; step 18 could check either the clj or the tabular form of score-all-games
;; into source control and use it as a regression test
;; (add clojure.java.io)
(use 'clojure.pprint)
(with-open [w (io/writer "scoring-table")]
    (binding [*out* w]
      (print-table (->> (generate-turn-inputs [:r :g :b :y] 4)
                        (score-inputs)))))

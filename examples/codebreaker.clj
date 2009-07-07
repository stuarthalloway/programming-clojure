(ns examples.codebreaker
  (:use clojure.contrib.seq-utils
        clojure.contrib.test-is
        clojure.set))

(defn count-if [f coll]
  (count (filter f coll)))

(defn count-exact-match
  "Number of exact matches (same color, same position)."
  [secret guess]
  (count-if true? (map = secret guess)))

(defn shared-frequencies
  "Returns a map with
    key:   colors that are in both the secret and the guess
    value: number of shared appearances of the color"
  [secret guess]
  (let [secret-dist (frequencies secret)
        guess-dist (frequencies guess)
        shared-colors (intersection (set (keys secret-dist))
                                    (set (keys guess-dist)))]
    (zipmap shared-colors
            (map #(min (secret-dist %) (guess-dist %))
                 shared-colors))))

(defn count-color-match
  "Number of color matches (same color, maybe different position)."
  [secret guess]
  (apply + (vals (shared-frequencies secret guess))))

(defn codebreaker-score
  "Returns a string of the form b*w*, with a number of bs equal
   to the exact matches, and a number of ws equal to the out-of-position
   color matches."
  [secret guess]
  (let [black-count (count-exact-match secret guess)
        white-count (- (count-color-match secret guess) black-count)]
    (apply str
           (concat (repeat black-count "b")
                   (repeat white-count "w")))))





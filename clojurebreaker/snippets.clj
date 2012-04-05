;; this file contains the intermediate steps in building the
;; Clojurebreaker game. See the clojurebreaker/src directory for the
;; completed code.

; START:exact-matches-shell
(defn exact-matches
  "Given two collections, return the number of positions where
   the collections contain equal items."
  [c1 c2])
; END:exact-matches-shell

; START:integers-closed
(defspec closed-under-addition
  +'
  [^long a ^long b]
  (assert (integer? %)))
; END:integers-closed

; START:incorrect-spec
(defspec incorrect-spec
  +'
  [^long a ^long b]
  (assert (< a %))
  (assert (< b %)))
; END:incorrect-spec

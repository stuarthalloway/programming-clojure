(ns examples.index-of-any)

; START: index-of-any
(defn index-filter [pred coll]
  (when pred (keep-indexed (fn [idx item] (when (pred item) idx)) coll)))

(defn ^{:test (fn []
                (assert (nil? (index-of-any #{\a} nil)))
                (assert (nil? (index-of-any #{\a} "")))
                (assert (nil? (index-of-any nil "foo")))
                (assert (nil? (index-of-any #{} "foo")))
                (assert (zero? (index-of-any #{\z \a} "zzabyycdxx")))
                (assert (= 3 (index-of-any #{\b \y} "zzabyycdxx")))
                (assert (nil? (index-of-any #{\z} "aba"))))}
  index-of-any
  [pred coll]
  (first (index-filter pred coll)))
; END: index-of-any

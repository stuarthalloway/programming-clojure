; (atom などの リファレンスは特別として)
; ミュータブルな変数なしに Clojure はどうプログラムを組んでいくか？

; Apacche Commons の StringUtils.indexOfAny の Clojure ver. を作ることで体験しよう。

(defn indexed [coll] (map-indexed vector coll))

(indexed "abcde")


(defn index-filter [pred coll]
    (when pred
        (for [[idx elt] (indexed coll) :when (pred elt)] idx)))

(index-filter #{\a \b} "abcdbbb")
; まぁこの辺は Scala は Haskell のリスト内包表記の強力さと同様だな。すごいよね。
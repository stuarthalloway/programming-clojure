; if の基本
(defn is-small1? [number]
    (if (< number 100) "yes"))

(is-small1? 50)
(is-small1? 500) ; この場合は nil になる

(defn is-small2? [number]
    (if (< number 100) "yes" "no"))

(is-small2? 50)
(is-small2? 500)

; do による副作用

(defn is-small3? [number]
    (if (< number 100)
    "yes"
    (do
        (println "Saw a big number" number)
        "no")))

; ターミナルで実行
; (is-small3? 200)



; loop と recur

(loop [result [], x 5]
    (if (zero? x)
    result
    (recur (conj result x) (dec x))))

(defn countdown [result x]
    (if (zero? x)
    result
    (recur (conj result x) (dec x))))
(countdown [] 5)


; まぁでも Clojure のコレクションは便利だから再帰を直接書くことは少ないよ！
(into [] (take 5 (iterate dec 5)))
(into [] (drop-last (reverse (range 6))))
(vec (reverse (rest (range 6))))
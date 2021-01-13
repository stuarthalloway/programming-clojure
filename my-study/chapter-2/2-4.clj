; let 束縛
; (let [bindings*] exprs*)
; let は最後の式の値を返す。OCaml や Scala と似てる。
(defn square-corners [bottom left size]
    (let [top (+ bottom size)
        right (+ left size)]
        [[bottom left] [top left] [top right] [bottom right]]))

(square-corners 1 2 3)


; 分配束縛
(defn greet-autho-1 [author]
    (println "Hello," (:first-name author)))

; ターミナルで。
; (greet-autho-1 {:last-name "Vinge" :first-name "Vernor"})

(defn greet-author-2 [{fname :first-name}]
    (println "Hello," fname))

; ターミナルで。
; (greet-author-2 {:last-name "Vinge" :first-name "Vernor"})
; 引数をオブジェクトとして受け取れるの、JS と似たノリだな。


; シーケンスもベクタとして分配束縛できる：
(let [[x y] [1 2 3]]
    [x y])


; 分配束縛しながら "全体" を束縛させることもできる：
(let [[x y :as coords] [1 2 3 4 5 6]]
    (str "x: " x ", y: " y ", total dimensions: " (count coords)))


; 分配束縛を使った練習
(require '[clojure.string :as str])
(defn ellipsize [words]
    (let [[w1 w2 w3] (str/split words #"\s+")]
        (str/join " " [w1 w2 w3 "..."])))

(ellipsize "The quick brown fo jumps over the lazy dog.")



; 名前空間
(def foo 10)
(resolve 'foo)

(in-ns 'myapp)

; こんなふうに Java を呼び出せるが、もしFQCNを書くのがめんどくさかったら、import すればよい。
(.exists (java.io.File. "/tmp"))
; File. のドットがわからなかったけど、インスタンスメンバーはこう呼び出すっぽい。
; https://sodocumentation.net/ja/clojure/topic/4036/java-interop
; clojure Java 呼び出す　でググった

(import '(java.io File))
(.exists (File. "/tmp"))


; import は Java 専用。他の名前空間にある Clojure の var を使いたければ、
; Java 呼び出しと同様、完全な名前を指定するか、名前空間を require するかする：
(use 'clojure.string) ; require と refer を同時にやるのであった。
(split "Something,separated,by,commas" #"\,")

; ただし、これだと split 関数を独自に定義できなくなってしまうので、
; require する名前空間に別名をつけるやり方で回避する：
(require '[clojure.string :as str])
(str/split "Something,separated,by,commas" #"\,")


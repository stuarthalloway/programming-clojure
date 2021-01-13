; 数値
; 以下の2つは結果が違う。
(/ 22 7)

(/ 22.0 7)

; 数学の意味で a = qb + r と書いたときにちょうど、数学と clojure を混ぜると
; a = b * (quot a b) + (rem a b) となる
; 22 = 7 * (quot 22 7) + (rem 22 7)
(quot 22 7)
(rem 22 7)
(+ (* 7 (quot 22 7)) (rem 22 7))

; シンボル
; clojure でいうシンボルとは、単に名前なのかな。(cf:p.12 "ものを指し示す名前だ")


; 文字列
(.toUpperCase "hello")
(str 1 2 nil 3)

(interleave "Attack at midnight" "The purple elephant chortled")
(str (interleave "Attack at midnight" "The purple elephant chortled")) ; LazeySeq が返ってくる。こういうときは apply する
(apply str (interleave "Attack at midnight" "The purple elephant chortled"))

; つまり、リスト一つを渡すんじゃなくて、リストの中身を引数として渡したいときに apply する。この例だと、str はリストではなく可変長引数なら受け取れる。
; https://twitter.com/lagenorhynque/status/1162270643808641024?s=20
; (twitter で clojure apply lang:ja で検索した)


; boolean
; true? false? nil? zero? などで それぞれチェックできる。(述語は ? をつける習慣)
(true? true)
(zero? 0)
(zero? 0.00)

; マップ、キーワード、レコード
(def inventors {"Lisp" "McCarthy" "Clojure" "Hickey"})

(inventors "Lisp")

; **Clojure の任意のデータをマップのキーとして使うことができる。**
; よくキーとして使われるのがキーワード。
(def inventors2 {:Lisp "McCarthy2" :Clojure "Hickey2"})
(inventors2 :Clojure)
(:Clojure inventors2) ; キーワードもまた関数として動作する。

(defrecord Book [title author])
(->Book "hoge" "foo")
(def b (->Book "Anathem" "Neal Stephenson"))

(:title b)
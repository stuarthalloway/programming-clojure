; require 混乱するのでもう一回整理

; p.16, p.17
; require すると名前空間つきで使えるようになる。

(require 'clojure.java.io)
(require 'examples.introduction)

(take 10 examples.introduction/fibs)
; (take 10 fibs) これはエラー。

; refer をすることで現在の名前空間から他の名前空間を参照できるようになる。
(refer 'examples.introduction)
(take 10 fibs)


; p.33 
; (require '[clojure.string :as str])


; p.40
; :as 構文で 名前空間に別名をつけられる。それが p.33 の構文。
; require する名前空間の中の関数と同じ名前を使いたい時に区別するために使う。

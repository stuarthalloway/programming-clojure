(require 'clojure.java.io)
(require 'examples.introduction)


(take 10 examples.introduction/fibs)

; refer '[namespaece]を使えば、現在の名前空間から別の名前空間を参照できる。
(refer 'examples.introduction)
(take 10 fibs)


; require と refer をまとめてやってくれるのが use

(use 'examples.introduction)
(take 10 fibs)

(use 'clojure.repl)

; REPL 上でないと動作しないが、ドキュメントを探せる
; (doc str)

; ドキュメンテーションは関数定義のすぐあとに書ける。
(defn hello2
    "Writes hello message to *out*. Calls you by username"
    [username]
    (println (str "Hello, " username)))

; これも REPL 上でないと動作しないが、find-doc で正規表現or文字列で検索できる
; (find-doc "something")

; これも REPL 上でないと動作しないが、source で ソースコードを読める。
; (source something)
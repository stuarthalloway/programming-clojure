; 引数違いの定義を同時に定義できる。しかも関数定義の中で自分自身を呼び出せる
; OOPにおけるオーバーロードみたいなことは 6章と8章でやる。
(defn greeting
    "Returns a greeting of the form 'Hello, username'
    Default username is 'world'."
    ([] (greeting "world"))
    ([username] (str "Hello, " username)))

(greeting)

; & を使うと可変長引数を受け取れる。
(defn date [person-1 person-2 & chaperones]
    (println person-1 "and" person-2
        "went out with" (count chaperones) "chaperones"))

; (VSCode 上では評価結果しか返されず、副作用は見えないので)
; これは 同じポートの nREPL に接続した ターミナルで試すとよい
; (date "Romeo" "Juliet" "Friar Lawrence" "Nurse")

; cf:  Clojure Development with Visual Studio Code 
; https://spin.atomicobject.com/2017/06/22/clojure-development-with-visual-studio-code/
; VSCode clojure Connecting to the REPL でググった


; 無名関数
(defn indexable-word? [word]
    (> (count word) 2))

(require '[clojure.string :as str])
(filter indexable-word?          (str/split "A fine day it is" #"\W+"))
; 無名関数のユースケースその1: 関数定義がめんどーなとき
; indexable-word? を定義するまでもなく...
(filter (fn [w] (> (count w) 2)) (str/split "A fine day it is" #"\W+"))
; さらに短いリテラルでも無名関数をかける。% で引数参照。
(filter #(> (count %) 2)         (str/split "A fine day it is" #"\W+"))


; 無名関数のユースケースその2: スコープを限定したいとき
; let のカッコに注意
(defn indexable-words [text]
    (let [indexable-word2? (fn [w] (> (count w) 2))]
        (filter indexable-word2? (str/split text #"\W+"))))

(indexable-words "a fine day it is")

; 無名関数のユースケースその3: 関数を実行時に動的に作る。関数を返す関数を作る時と言ってもよい。
(defn make-greeter [greeting-prefix]
    (fn [username] (str greeting-prefix ", " username)))

(def hello-greeting (make-greeter "Hello"))
(def aloha-greeting (make-greeter "Aloha"))

(hello-greeting "world")
(aloha-greeting "world")
((make-greeter "Howdy") "pardner")
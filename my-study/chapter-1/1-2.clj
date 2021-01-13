; example of atom

(def visitors (atom #{}))

; atom の状態を書き換える。
(swap! visitors conj "Stu")

; atom の状態を参照できる
@visitors

; 上の２つを使えば、状態を保持できる関数ができる。
(defn hello
    "Writes hello message to *out*. Calls you by username.
    Knows if you have been here before."
    [username]
    (swap! visitors conj username)
    (str "Hello, " username))


(hello "Rich")


@visitors
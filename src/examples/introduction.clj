(ns examples.introduction)

; START:blank
(defn blank? [str]
  (every? #(Character/isWhitespace %) str))
; END:blank

(def accounts (ref #{}))
(defstruct account :id :balance)

; START:hello-world
(defn hello-world [username] 
  (println (format "Hello, %s" username)))  
; END:hello-world

(def fibs (lazy-cat [0 1] (map + fibs (rest fibs))))

; START:hello-docstring
(defn hello
  "Writes hello message to *out*. Calls you by username"
  [username]
  (println (str "Hello, " username)))
; END:hello-docstring
(def hello-docstring hello)

(def visitors (atom #{}))

; START:hello
(defn hello 
  "Writes hello message to *out*. Calls you by username.
  Knows if you have been here before."
  [username]
  (swap! visitors conj username)
  (str "Hello, " username))
; END:hello

(def hello-with-memory hello)
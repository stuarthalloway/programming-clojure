(ns examples.introduction)

; START:blank
(defmulti blank? class)
(defmethod blank? String [s] (every? #(Character/isWhitespace %) s))
(defmethod blank? nil [_] true)
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

(def visitors (ref #{}))
; START:hello
(defn hello [username]
  (dosync 
    (let [past-visitor (@visitors username)]    ; <label id="code.hello.past-visitor"/>
      (alter visitors conj username)            ; <label id="code.hello.alter"/>  
      (if past-visitor                      	; <label id="code.hello.if"/>
	(str "Welcome back, " username)
	(str "Hello, " username)))))            ; <label id="code.hello.else"/>
; END:hello

(def hello-with-memory hello)


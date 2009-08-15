(ns examples.introduction)

; START:blank
(defn blank? [s] (every? #(Character/isWhitespace %) s))
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
(defn hello 
  "Returns a hello message, calling you by username.
  Knows if you have been here before."
  [username]
  (dosync 
    (let [past-visitor (@visitors username)]    ; <label id="code.hello.past-visitor"/>
      (if past-visitor                      	
	(str "Welcome back, " username)         ; <label id="code.hello.if"/>
	(do 
	  (alter visitors conj username)        ; <label id="code.hello.alter"/>  
	  (str "Hello, " username))))))         ; <label id="code.hello.else"/>
; END:hello

(def hello-with-memory hello)


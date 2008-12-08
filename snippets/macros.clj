;; NOT for execution. These are excerpts from Clojure
;; and are subject to the Clojure License, repeated below:

;   Copyright (c) Rich Hickey. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Common Public License 1.0 (http://opensource.org/licenses/cpl.php)
;   which can be found in the file CPL.TXT at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

; START: and
(defmacro and
  ([] true)
  ([x] x)
  ([x & rest]
     `(let [and# ~x]   ; <label id="macros.and.let"/> 
      (if and# (and ~@rest) and#)))) ; <label id="macros.and.if"/>
; END: and

; START: with-out-str
(defmacro with-out-str
  [& body]  ; <label id="macros.with.varargs"/>
  `(let [s# (new java.io.StringWriter)] ; <label id="macros.with.let"/>
     (binding [*out* s#] ; <label id="macros.with.bindings"/>
       ~@body            ; <label id="macros.with.splice"/>
       (str s#))))       ; <label id="macros.with.result"/>
; END: with-out-str

; START: defstruct
(defmacro defstruct
  [name & keys]
  `(def ~name (create-struct ~@keys)))
; END: defstruct

; START: declare
(defmacro declare
  [& names] `(do ~@(map #(list 'def %) names)))
; END: declare
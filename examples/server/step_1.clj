(ns examples.server.step-1)

; START: defservlet
(use 'compojure.http)
(defservlet snippet-servlet
  "Create and view snippets."
  (GET "/ping" "pong")

  (ANY "*"
    (page-not-found)))
; END: defservlet

; START: defserver
(use 'compojure.jetty)
(defserver snippet-server
  {:port 8080}
  "/*" snippet-servlet)
; END: defserver



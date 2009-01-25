(ns examples.server.step-1)

; START: defservlet
(use '[compojure.http servlet routes helpers])
(defservlet snippet-servlet
  "Create and view snippets."
  (GET "/ping" "pong")

  (ANY "*"
    (page-not-found)))
; END: defservlet

; START: defserver
(use 'compojure.server.jetty)
(defserver snippet-server
  {:port 8080}
  "/*" snippet-servlet)
; END: defserver



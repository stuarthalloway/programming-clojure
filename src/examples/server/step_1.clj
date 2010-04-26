(ns examples.server.step-1)

; START: defroutes
(use 'compojure)
(defroutes snippet-app
  "Create and view snippets."
  (GET "/ping" "pong")

  (ANY "*"
    (page-not-found)))
; END: defroutes

; START: run-server
(run-server {:port 8080}
  "/*" (servlet snippet-app))
; END: run-server



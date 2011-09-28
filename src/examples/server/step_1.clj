(ns examples.server.step-1)

; START: defroutes
(use '[compojure.core :only (defroutes GET)])
(use '[ring.adapter.jetty :only (run-jetty)])
(require '[compojure.route :as route])

(defroutes routes
  (GET "/ping" [] "pong")
  (route/not-found "<h2>Not Found</h2>"))
; END: defroutes

; START: run-server
(run-jetty routes {:port 8080 :join? false})
; END: run-server



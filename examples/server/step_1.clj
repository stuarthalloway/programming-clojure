; START: 
(ns examples.server.step-1
  (:use [compojure http jetty]))

(defservlet snippet-servlet
  "Create and view snippets."
  (GET "/ping" "Pong")

  (ANY "*"
    (page-not-found)))

(defserver snippet-server
  {:port 8080}
  "/*" snippet-servlet)

(start snippet-server)



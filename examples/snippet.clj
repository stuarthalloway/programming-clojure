(use 'compojure.http)
(use 'compojure.jetty)

(defservlet ping-servlet
  "test"
  (GET "/ping" "Pong"))

(defserver snippet-server
  {:port 8080}
  "/*" ping-servlet)

(start snippet-server)
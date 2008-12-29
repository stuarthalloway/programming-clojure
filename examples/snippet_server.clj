(use 'compojure.http)
(use 'compojure.jetty)
(use 'examples.snippet)

(defservlet snippet-servlet
  "create and view snippets"
  (GET "/ping" "Pong")

  (POST "/"
    (if-let [id (insert-snippet params)]
      (redirect-to (str "/" id))
      (redirect-to "/"))))

(defserver snippet-server
  {:port 8080}
  "/*" snippet-servlet)

(start snippet-server)
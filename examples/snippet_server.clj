(ns examples.snippet-server
  (:use [compojure html http jetty file-utils] 
        examples.snippet))

(reset-snippets)

(defn show-snippet [id]
  (let [snippet (select-snippet id)]
    (html
     [:div [:pre [:code {:class "clojure"} (:body snippet)]]]
     [:div {:class "date"} (:created_at snippet)])))
    
(defservlet snippet-servlet
  "create and view snippets"
  (GET "/ping" "Pong")

  (GET "/" 
    (html
      (form-to [POST "/"]
        (text-area {:rows 20} "body")
        [:br]
        (submit-button "Save"))))

  (GET "/:id"
    (html
      [:head
        (include-js "/javascripts/code-highlighter.js" "/javascripts/clojure.js")
        (include-css "/stylesheets/code-highlighter.css")]
      [:body
        (show-snippet (route :id))]))

  (GET "/javascripts/:name.js"
    (file (str "javascripts/" (route :name) ".js")))

  (GET "/stylesheets/:name.css"
    (file (str "stylesheets/" (route :name) ".css")))

  (POST "/"
    (if-let [id (insert-snippet params)]
      (redirect-to (str "/" id))
      (redirect-to "/"))))

(defserver snippet-server
  {:port 8080}
  "/*" snippet-servlet)

(start snippet-server)
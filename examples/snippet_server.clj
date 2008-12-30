(ns examples.snippet-server
  (:use [compojure html http jetty file-utils] 
        examples.snippet))

(reset-snippets)

(defn show-snippet [id]
  (let [snippet (select-snippet id)]
    (html
     [:div [:pre [:code {:class "clojure"} (:body snippet)]]]
     [:div {:class "date"} (:created_at snippet)])))
    
(defn layout [title & body]
  (html
    [:head
      [:title title]
      (include-js "/public/javascripts/code-highlighter.js" "/public/javascripts/clojure.js")
      (include-css "/public/stylesheets/code-highlighter.css")]
    [:body
      [:h2 title]
      body]))

(defservlet snippet-servlet
  "create and view snippets"
  (GET "/ping" "Pong")

  (GET "/"
    (layout "Create a Snippet"
      (form-to [POST "/"]
        (text-area {:rows 20} "body")
        [:br]
        (submit-button "Save"))))

  (GET "/:id"
    (layout (str "Snippet " (route :id))
      (show-snippet (route :id))))

  (GET "/public/*"
    (serve-file "public" (route :*)))

  (POST "/"
    (if-let [id (insert-snippet params)]
      (redirect-to (str "/" id))
      (redirect-to "/")))

  (ANY "*"
    (page-not-found)))

(defserver snippet-server
  {:port 8080}
  "/*" snippet-servlet)

(start snippet-server)
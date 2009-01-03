(ns examples.server.complete
  (:use [compojure html http jetty file-utils] 
        examples.snippet))

(defn layout [title & body]
  (html
    [:head
      [:title title]
      (include-js "/public/javascripts/code-highlighter.js" "/public/javascripts/clojure.js")
      (include-css "/public/stylesheets/code-highlighter.css")]
    [:body
      [:h2 title]
      body]))

(defn new-snippet []
  (layout "Create a Snippet"
    (form-to [POST "/"]
      (text-area {:rows 20} "body")
      [:br]
      (submit-button "Save"))))

(defn create-snippet [body]
  (if-let [id (insert-snippet body)]
    (redirect-to (str "/" id))
    (redirect-to "/")))

(defn show-snippet [id]
  (layout (str "Snippet " id)
    (let [snippet (select-snippet id)]
      (html
       [:div [:pre [:code {:class "clojure"} (:body snippet)]]]
       [:div {:class "date"} (:created_at snippet)]))))

(defservlet snippet-servlet
  "Create and view snippets."
  (GET "/ping" "Pong")

  (GET "/" 
     (new-snippet))

  (GET "/:id"
     (show-snippet (route :id)))

  (GET "/public/*"
    (serve-file (route :*)))

  (POST "/"
    (create-snippet (:body params)))	

  (ANY "*"
    (page-not-found)))

(defserver snippet-server
  {:port 8080}
  "/*" snippet-servlet)

(start snippet-server)
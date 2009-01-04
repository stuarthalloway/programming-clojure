(ns reader.snippet-server
  (:use [compojure html http jetty file-utils] 
        examples.snippet))

(defn new-snippet []
  (html
   (form-to [POST "/"]
     (text-area {:rows 20 :cols 73} "body")
     [:br]
     (submit-button "Save"))))

(defn create-snippet [body]
  (if-let [id (insert-snippet body)]
    (redirect-to (str "/" id))
    (redirect-to "/")))

(defn show-snippet [id]
  (let [snippet (select-snippet id)]
    (html
     [:div [:pre [:code (:body snippet)]]]
     [:div (:created_at snippet)])))

(defservlet snippet-servlet
  "Create and view snippets."
  (GET "/ping" "Pong")

  (GET "/" 
     (new-snippet))

  (GET "/:id"
     (show-snippet (route :id)))

  (POST "/"
    (create-snippet (:body params)))	

  (ANY "*"
    (page-not-found)))

(use 'compojure.jetty)
(defserver snippet-server
  {:port 8080}
  "/*" snippet-servlet)



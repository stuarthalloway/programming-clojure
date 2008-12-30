(ns examples.snippet-server
  (:use [compojure html http jetty] 
        examples.snippet))

(reset-snippets)

(defn show-snippet [id]
  (let [snippet (select-snippet id)]
    (html
     [:div {:class "body"} (:body snippet)]
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
    (show-snippet (route :id)))

  (POST "/"
    (if-let [id (insert-snippet params)]
      (redirect-to (str "/" id))
      (redirect-to "/"))))

(defserver snippet-server
  {:port 8080}
  "/*" snippet-servlet)

(start snippet-server)
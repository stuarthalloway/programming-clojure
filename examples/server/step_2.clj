(ns examples.server.step-2
  (:use [compojure.http helpers routes servlet]
        [compojure.html form-helpers page-helpers]
	compojure.html
        compojure.server.jetty
        compojure.file-utils 
        examples.snippet))

; START: new-snippet
(defn new-snippet []
  (html
    (form-to [POST "/"]
      (text-area {:rows 20 :cols 73} "body")
      [:br]
      (submit-button "Save"))))
; END: new-snippet

; START: create-snippet
(defn create-snippet [body]
  (if-let [id (insert-snippet body)]
    (redirect-to (str "/" id))
    (redirect-to "/")))
; END: create-snippet

; START: show-snippet
(defn show-snippet [id]
  (let [snippet (select-snippet id)]
    (html
     [:div [:pre [:code (:body snippet)]]]
     [:div (:created_at snippet)])))
; END: show-snippet

(defservlet snippet-servlet
  "Create and view snippets."
  (GET "/ping" "Pong")

  ; START: handlers
  (GET "/" 
     (new-snippet))

  (GET "/:id"
     (show-snippet (route :id)))

  (POST "/"
    (create-snippet (:body params)))	
  ; END: handlers

  (ANY "*"
    (page-not-found)))

(defserver snippet-server
  {:port 8080}
  "/*" snippet-servlet)




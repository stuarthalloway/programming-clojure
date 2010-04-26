(ns examples.server.step-3
  (:use compojure examples.snippet))

; START: layout
(defn layout [title & body]
  (html
    [:head
      [:title title]
      (include-js "/public/javascripts/code-highlighter.js" 
		  "/public/javascripts/clojure.js")
      (include-css "/public/stylesheets/code-highlighter.css")]
    [:body
      [:h2 title]
      body]))
; END: layout

; START: new-snippet
(defn new-snippet []
  (layout "Create a Snippet"
    (form-to [:post "/"]
      (text-area {:rows 20 :cols 73} "body")
      [:br]
      (submit-button "Save"))))
; END: new-snippet

(defn create-snippet [body]
  (if-let [id (insert-snippet body)]
    (redirect-to (str "/" id))
    (redirect-to "/")))

; START: show-snippet
(defn show-snippet [id]
  (layout (str "Snippet " id)
    (let [snippet (select-snippet id)]
      (html
       [:div [:pre [:code.clojure (:body snippet)]]]
       [:div (:created_at snippet)]))))
; END: show-snippet

(defroutes snippet-app
  "Create and view snippets."
  (GET "/ping" "Pong")

  (GET "/" 
     (new-snippet))

  (GET "/:id"
     (show-snippet (params :id)))

  (POST "/"
    (create-snippet (:body params)))	

  ; START: public
  (GET "/public/*"
    (or (serve-file (params :*)) :next))
  ; END: public

  (ANY "*"
    (page-not-found)))

(ensure-snippets-table-exists)
(run-server {:port 8080}
  "/*" (servlet snippet-app))


(ns examples.server.step-2
  (:use [compojure.core :only (defroutes GET POST)]
        [examples.snippet]
        [ring.util.response :only (redirect)])
  (:require [hiccup.core :as hiccup]
            [hiccup.form-helpers :as form]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.adapter.jetty :as ring]))

; START: new-snippet
(defn new-snippet []
  (hiccup/html
    (form/form-to [:post "/"]
      (form/text-area {:rows 20 :cols 73} "body")
      [:br]
      (form/submit-button "Save"))))
; END: new-snippet

; START: create-snippet
(defn create-snippet [body]
  (if-let [id (insert-snippet body)]
    (redirect (str "/" id))
    (redirect "/")))
; END: create-snippet

; START: show-snippet
(defn show-snippet [id]
  (let [snippet (select-snippet id)]
    (hiccup/html
     [:div [:pre [:code (:body snippet)]]]
     [:div (:created_at snippet)])))
; END: show-snippet

; START: routes
(defroutes routes
  (GET "/" [] (new-snippet))
  (GET "/:id" [id] (show-snippet id))
  (POST "/" [body] (create-snippet body))	
  (route/not-found "<h2>Not Found</h2>"))
; END: routes

; START: site
(def application (handler/site routes))
; END: site
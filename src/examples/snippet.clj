(ns examples.snippet)

; START: create-snippets
(require '[clojure.java.jdbc :as sql])
(defn create-snippets []
  (sql/create-table :snippets
                    [:id :int "IDENTITY" "PRIMARY KEY"]
                    [:body :varchar "NOT NULL"]
                    [:created_at :datetime]))
; END: create-snippets

; START: db
; replace "snippet-db" with a full path!
(def db {:classname "org.hsqldb.jdbcDriver"
         :subprotocol "hsqldb"
         :subname "file:snippet-db"})
; END: db

(defn drop-snippets []
  (try
    (sql/drop-table :snippets)
    (catch Exception e)))

; START: insert-snippets
(defn insert-snippets []
  (let [timestamp (java.sql.Timestamp. (.getTime (java.util.Date.)))]
    (sql/insert-records :snippets
                        {:body "(println :boo)" :created_at timestamp}
                        {:body "(defn foo [] 1)" :created_at timestamp})))
; END: insert-snippets

(defn sample-snippets []
  (sql/with-connection db
    (drop-snippets)
    (create-snippets)
    (insert-snippets)))

(defn reset-snippets []
  (sql/with-connection db
    (drop-snippets)
    (create-snippets)))

(defn ensure-snippets-table-exists []
  (try
    (sql/with-connection db (create-snippets))
    (catch Exception _)))


; START: print-snippets
(defn print-snippets []
  (sql/with-query-results res ["select * from snippets"]
    (println res)))
; END: print-snippets

; START: broken-select-snippets
; Broken!
(defn select-snippets []
  (sql/with-query-results res ["select * from snippets"] res))
; END: broken-select-snippets

(def broken-select-snippets select-snippets)

(defmulti coerce (fn [dest-class src-inst] [dest-class (class src-inst)]))
(defmethod coerce [Long String] [_ inst] (Long/parseLong inst))
(defmethod coerce :default [dest-cls obj] (cast dest-cls obj))

; START: select-snippets-doall
(defn select-snippets []
  (sql/with-connection db
    (sql/with-query-results res
      ["select * from snippets"]
      (doall res))))
; END: select-snippets-doall

; START: sql-query
(defn sql-query [q]
  (sql/with-query-results res
    q
    (doall res)))
; END: sql-query

(defn select-snippet [id]
  (sql/with-connection db
    (first (sql-query ["select * from snippets where id = ?" (coerce Long id)]))))

; START: last-created-id
(defn last-created-id
  "Extract the last created id. Must be called in a transaction
   that performed an insert. Expects HSQLDB return structure of
   the form [{:@p0 id}]."
  []
  (first (vals (first (sql-query ["CALL IDENTITY()"])))))
; END: last-created-id

; START: insert-snippet
(defn insert-snippet [body]
  (let [timestamp (java.sql.Timestamp. (.getTime (java.util.Date.)))]
    (sql/with-connection db
      (sql/transaction
       (sql/insert-records :snippets
                           {:body body :created_at timestamp})
       (last-created-id)))))
; END: insert-snippet

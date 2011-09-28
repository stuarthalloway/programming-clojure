(ns examples.concurrency
  (:use examples.chat))

; START: counter
(def counter (ref 0))
; END: counter

; START: next-counter
(defn next-counter [] (dosync (alter counter inc)))
; END: next-counter

; START: slow-double
(defn ^:dynamic slow-double [n]
  (Thread/sleep 100)
  (* n 2))
; END: slow-double

; START: calls-slow-double
(defn calls-slow-double []
  (map slow-double [1 2 1 2 1 2]))
; END: calls-slow-double

; START: demo-memoize
(defn demo-memoize []
  (time 
   (dorun
    (binding [slow-double (memoize slow-double)]
      (calls-slow-double)))))
; END: demo-memoize	 

; START: backup-agent
(def backup-agent (agent "output/messages-backup.clj"))
; END: backup-agent

; START: add-message-with-backup
(defn add-message-with-backup [msg]
  (dosync 
   (let [snapshot (commute messages conj msg)]
     (send-off backup-agent (fn [filename]
			      (spit filename snapshot)
			      filename))
     snapshot)))
; END: add-message-with-backup

  

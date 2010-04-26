(ns lancet.step-1-repl)

; START: ant-project
(def
 #^{:doc "Dummy ant project to keep Ant tasks happy"} 	; <label id="interop.lancet.doc"/>
 ant-project                                            ; <label id="interop.lancet.var"/>
 (let [proj (org.apache.tools.ant.Project.)             ; <label id="interop.lancet.let"/>
       logger (org.apache.tools.ant.NoBannerLogger.)]
   (doto logger                                         ; <label id="interop.lancet.logger"/>
     (.setMessageOutputLevel org.apache.tools.ant.Project/MSG_INFO)
     (.setOutputPrintStream System/out)
     (.setErrorPrintStream System/err))
   (doto proj                                           ; <label id="interop.lancet.proj"/>
     (.init)                                                                             
     (.addBuildListener logger))))
; END: ant-project

; START: instantiate-task
(defn instantiate-task [project name]
  (let [task (.createTask project name)]
    (doto task
      (.init)
      (.setProject project))))
; END: instantiate-task

; START: safe-instantiate-task
(use '[clojure.contrib.except :only (throw-if)])
(defn safe-instantiate-task [project name]
  (let [task (.createTask project name)]
    (throw-if (nil? task) 
	      IllegalArgumentException (str "No task named " name))
    (doto task
      (.init)
      (.setProject project))))
; END: safe-instantiate-task


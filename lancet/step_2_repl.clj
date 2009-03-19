(ns lancet.step-2-repl)

(def
 #^{:doc "Dummy ant project to keep Ant tasks happy"} 	
 ant-project                                            
 (let [proj (org.apache.tools.ant.Project.)             
       logger (org.apache.tools.ant.NoBannerLogger.)]
   (doto logger                                         
     (.setMessageOutputLevel org.apache.tools.ant.Project/MSG_INFO)
     (.setOutputPrintStream System/out)
     (.setErrorPrintStream System/err))
   (doto proj                                           
     (.init)                                                                             
     (.addBuildListener logger))))

; START: property-descriptor
(import '(java.beans Introspector))
(defn property-descriptor [inst prop-name]
  (first
   (filter #(= (name prop-name) (.getName %)) 
	   (.getPropertyDescriptors 
	    (Introspector/getBeanInfo (class inst))))))
; END: property-descriptor

; START: set-property!
(use '[clojure.contrib.except :only (throw-if)])
(defn set-property! [inst prop value]
  (let [pd (property-descriptor inst prop)]   ; <label id="sequences.lancet.let"/> 
    (throw-if (nil? pd) (str "No such property " prop)) ; <label id="sequences.lancet.throw-if"/> 
    (.invoke (.getWriteMethod pd) inst (into-array [value])))) ; <label id="sequences.lancet.into-array"/> 
; END: set-property!

; START: set-properties!
(defn set-properties! [inst prop-map]
  (doseq [[k v] prop-map] (set-property! inst k v))) 
; END: set-properties!

; START: instantiate-task
(defn instantiate-task [project name props]
  (let [task (.createTask project name)]
    (throw-if (nil? task) (str "No task named " name))
    (doto task
      (.init)
      (.setProject project)
      (set-properties! props)) 
    task))
; END: safe-instantiate-task


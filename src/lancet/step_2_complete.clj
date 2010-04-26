(ns lancet.step-2-complete
    (:use clojure.contrib.except)
    (:import (java.beans Introspector)))
	     
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

(defn property-descriptor [inst prop-name]
  (first
   (filter #(= (name prop-name) (.getName %)) 
	   (.getPropertyDescriptors 
	    (Introspector/getBeanInfo (class inst))))))

(defn set-property! [inst prop value]
  (let [pd (property-descriptor inst prop)]   
    (throw-if (nil? pd) (str "No such property " prop)) 
    (.invoke (.getWriteMethod pd) inst (into-array [value])))) 

(defn set-properties! [inst prop-map]
  (doseq [[k v] prop-map] (set-property! inst k v))) 

(defn instantiate-task [project name props]
  (let [task (.createTask project name)]
    (throw-if (nil? task) (str "No task named " name))
    (doto task
      (.init)
      (.setProject project)
      (set-properties! props))
    task))



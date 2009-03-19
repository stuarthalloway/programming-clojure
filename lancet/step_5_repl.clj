(ns lancet.step-5-repl
    (:use clojure.contrib.except)
    (:import (java.beans Introspector)))

; START: coerce
(defmulti coerce 
  (fn [dest-class src-inst] [dest-class (class src-inst)]))
; END: coerce

; START: coerce-file
(defmethod coerce [java.io.File String] [_ str] 
  (java.io.File. str))
; END: coerce-file

; START: coerce-boolean
(defmethod coerce [Boolean/TYPE String] [_ str]
  (contains? #{"on" "yes" "true"} (.toLowerCase str)))
; END: coerce-boolean

; START: coerce-default
(defmethod coerce :default [dest-cls obj] (cast dest-cls obj))
; END: coerce-default

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

; START: get-property-class
(defn get-property-class [write-method]
  (first (.getParameterTypes write-method)))
; END: get-property-class

; START: set-property
(defn set-property! [inst prop value]
  (let [pd (property-descriptor inst prop)]
    (throw-if (nil? pd) (str "No such property " prop))
    (let [write-method (.getWriteMethod pd)
	  dest-class (get-property-class write-method)]
      (.invoke 
       write-method inst (into-array [(coerce dest-class value)])))))
; END: set-property

(defn set-properties! [inst prop-map]
  (doseq [[k v] prop-map] (set-property! inst k v))) 

(defn instantiate-task [project name props]
  (let [task (.createTask project name)]
    (throw-if (nil? task) (str "No task named " name))
    (doto task
      (.init)
      (.setProject project)
      (set-properties! props))))



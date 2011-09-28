(ns examples.tasklist
  (:gen-class 
   :extends org.xml.sax.helpers.DefaultHandler
   :init init
   :state state)
  (:use [clojure.java.io :only (reader)])
  (:import [java.io File]
           [org.xml.sax InputSource]
	   [org.xml.sax.helpers DefaultHandler]
	   [javax.xml.parsers SAXParserFactory]))

; START: task-list
(defn task-list [arg]
  (let [handler (new examples.tasklist)] ; <label id="code.tasklist.classname"/>
    (.. SAXParserFactory newInstance newSAXParser
	(parse (InputSource. (reader (File. arg)))
	       handler))
    @(.state handler))) ; <label id="code.tasklist.statename"/>
; END: task-list

; START: init
(defn -init []
  [[] (atom [])])
; END: init

; START: startElement
(defn -startElement
  [this uri local qname atts]
  (when (= qname "target")
    (swap! (.state this) conj (.getValue atts "name")))) ; <label id="code.tasklist.update"/>
; END: startElement

; START: main
(defn -main [& args]
  (doseq [arg args]
    (println (task-list arg))))
; END: main

(ns examples.tasklist
  (:use [clojure.contrib.duck-streams :only (reader)])
  (:import [java.io File]
           [org.xml.sax InputSource]
	   [org.xml.sax.helpers DefaultHandler]
	   [javax.xml.parsers SAXParserFactory])
  (:gen-class
   :extends org.xml.sax.helpers.DefaultHandler
   :init init
   :state state))

(defn task-list [arg]
  (let [handler (new examples.tasklist)]
    (.. SAXParserFactory newInstance newSAXParser
	(parse (InputSource. (reader (File. arg)))
	       handler))
    @(.state handler)))

(defn -init []
  [[] (atom [])])

(defn -startElement
  [this uri local qname atts]
  (when (= qname "target")
    (swap! (.state this) conj (.getValue atts "name"))))

(defn -main [& args]
  (doseq [arg args]
    (println (task-list arg))))
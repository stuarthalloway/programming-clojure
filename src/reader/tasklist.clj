(ns reader.tasklist
  (:gen-class ; <label id="code.tasklist.genclass"/>
   :extends org.xml.sax.helpers.DefaultHandler ; <label id="code.tasklist.extends"/>
   :state state ; <label id="code.tasklist.state"/>
   :init init) ; <label id="code.tasklist.init"/>
  (:use [clojure.contrib.duck-streams :only (reader)])
  (:import [java.io File]
           [org.xml.sax InputSource]
	   [org.xml.sax.helpers DefaultHandler]
	   [javax.xml.parsers SAXParserFactory]))

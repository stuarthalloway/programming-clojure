(ns examples.interop)

; START:sum-to
; performance demo only, don't write code like this
(defn sum-to [n]
  (loop [i 1 sum 0]
    (if (<= i n)
      (recur (inc i) (+ i sum))
      sum)))
; END:sum-to

; START:integer-sum-to
(defn integer-sum-to [n]
  (let [n (int n)]
    (loop [i (int 1) sum (int 0)]
      (if (<= i n)
	(recur (inc i) (+ i sum))
	sum))))
; END:integer-sum-to

; START:unchecked-sum-to
(defn unchecked-sum-to [n]
  (let [n (int n)]
    (loop [i (int 1) sum (int 0)]
      (if (<= i n)
	(recur (inc i) (unchecked-add i sum))
	sum))))
; END:unchecked-sum-to

(defn better-sum-to [n]
  (reduce + (range 1 (inc n))))

(defn best-sum-to [n]
  (/ (* n (inc n)) 2))
; TODO: a better timer?

(defn painstakingly-create-array []
  (let [arr (make-array String 5)]
    (aset arr 0 "Painstaking")
    (aset arr 1 "to")
    (aset arr 2 "fill")
    (aset arr 3 "in")
    (aset arr 4 "arrays")
    arr))

; START:xml-imports
(import '(org.xml.sax InputSource)
	'(org.xml.sax.helpers DefaultHandler)
	'(java.io StringReader)
	'(javax.xml.parsers SAXParserFactory))
; END:xml-imports

; START:print-element-handler
(def print-element-handler
     (proxy [DefaultHandler] [] 
       (startElement            
	[uri local qname atts] 
	(println (format "Saw element: %s" qname)))))
; END:print-element-handler

; START:demo-sax-parse
(defn demo-sax-parse [string handler]
  (.. SAXParserFactory newInstance newSAXParser 
      (parse (InputSource. (StringReader. string)) 
	     handler)))
; END:demo-sax-parse

(defn demo-threads []
; START:demo-threads
(dotimes [i 5]
  (.start
   (Thread.
    (fn []
      (Thread/sleep (rand 500))
      (println (format "Finished %d on %s" i (Thread/currentThread)))))))
; END:demo-threads
)

(defn demo-try-finally []
; START:try-finally
(try
 (throw (Exception. "something failed"))
 (finally
  (println "we get to clean up")))
; END:try-finally
)  

; START:poor-class-available
; not caller-friendly
(defn class-available? [class-name]
  (Class/forName class-name))
; END:poor-class-available
(def poor-class-available? class-available?)

; START:better-class-available
(defn class-available? [class-name]
  (try 
   (Class/forName class-name) true
   (catch ClassNotFoundException _ false)))    
; END:better-class-available

; START:untyped-describe-class
(defn describe-class [c]
  {:name (.getName c)
   :final (java.lang.reflect.Modifier/isFinal (.getModifiers c))})
; END:untyped-describe-class
(def untyped-describe-class describe-class)

; START:typed-describe-class
(defn describe-class [#^Class c]
  {:name (.getName c)
   :final (java.lang.reflect.Modifier/isFinal (.getModifiers c))})
; END:typed-describe-class
(def typed-describe-class describe-class)



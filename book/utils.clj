(ns book.utils
    (:use [clojure.contrib.duck-streams :only (spit)]))

(defn classloader-seq 
  ([] (classloader-seq (clojure.lang.RT/baseLoader)))
  ([cl] 
     (loop [loaders (vector cl)]
       (if (nil? (last loaders))
	 (drop-last loaders)
	 (recur (conj loaders (.getParent (last loaders))))))))

(defn classpath-url-seq [& args]
  (map (memfn toExternalForm)
       (reduce concat
	       (map (memfn getURLs)
		    (apply classloader-seq args)))))

; TODO: update book or add to Clojure
(defmacro ?.
  "like .. but drops out on null object"
  ([x form] 
     `(. ~x ~form))
  ([x form & more] 
     `(if-let x# (. ~x ~form) (.? x# ~@more))))

(defn files-on-classpath-at [path]
  (when-let [url (.findResource (.getContextClassLoader (Thread/currentThread)) path)]
    (file-seq (java.io.File. (.getFile url)))))

(defmacro defdemo [nm & forms]
  `(defn ~nm []
     (let [result# (with-out-str (format-for-book ~@forms))]
       (spit ~(str "output/" (name nm) ".out") result#)
       result#)))
  
(defmacro format-for-book [& forms]
  `(do
     ~@(map (fn [form]
	      (if (instance? String `~form)
		`(do
		   (println ~form)
		   (print "-> ")
		   (prn (load-string ~form))
		   (println))
		`(do
		   (prn '~form)
		   (print "-> ")
		   (prn ~form)
		   (println))))
	    forms)))

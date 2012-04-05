(ns examples.sequences
    (:use examples.utils clojure.set clojure.xml))

; START:song
(def song {:name "Agnus Dei"
	   :artist "Krzysztof Penderecki"
	   :album "Polish Requiem"
	   :genre "Classical"})
; END:song

; START:compositions
(def compositions 
  #{{:name "The Art of the Fugue" :composer "J. S. Bach"}
    {:name "Musical Offering" :composer "J. S. Bach"}
    {:name "Requiem" :composer "Giuseppe Verdi"}
    {:name "Requiem" :composer "W. A. Mozart"}})
(def composers
  #{{:composer "J. S. Bach" :country "Germany"}
    {:composer "W. A. Mozart" :country "Austria"}
    {:composer "Giuseppe Verdi" :country "Italy"}})
(def nations
  #{{:nation "Germany" :language "German"}
    {:nation "Austria" :language "German"}
    {:nation "Italy" :language "Italian"}})

; END:compositions

; TODO: add pretty-print that works with book margins.
(defdemo demo-map-builders
  (assoc song :kind "MPEG Audio File")
  (dissoc song :genre)
  (select-keys song [:name :artist])
  (merge song {:size 8118166 :time 507245}))

(defdemo demo-merge-with
  (merge-with 
   concat 
   {:flintstone, ["Fred"], :rubble ["Barney"]}
   {:flintstone, ["Wilma"], :rubble ["Betty"]}
   {:flintstone, ["Pebbles"], :rubble ["Bam-Bam"]}))

; START:sets
(def languages #{"java" "c" "d" "clojure"})
(def beverages #{"java" "chai" "pop"})
; END: sets

(defdemo demo-mutable-re
; START:mutable-re
; don't do this!
(let [m (re-matcher #"\w+" "the quick brown fox")]
  (loop [match (re-find m)]
    (when match
      (println match)
      (recur (re-find m)))))
; END:mutable-re
)

; START:filter      
(defn minutes-to-millis [mins] (* mins 1000 60))

(defn recently-modified? [file]
  (> (.lastModified file) 
     (- (System/currentTimeMillis) (minutes-to-millis 30))))
; END:filter

; START:clojure-loc
(use '[clojure.java.io :only (reader)])
(defn non-blank? [line] (if (re-find #"\S" line) true false))

(defn non-svn? [file] (not (.contains (.toString file) ".svn")))

(defn clojure-source? [file] (.endsWith (.toString file) ".clj"))

(defn clojure-loc [base-file]
  (reduce 
   +
   (for [file (file-seq base-file) 
	 :when (and (clojure-source? file) (non-svn? file))]
     (with-open [rdr (reader file)]
       (count (filter non-blank? (line-seq rdr)))))))
; END:clojure-loc

(defn demo-xml-seq []
; START:xml-seq
(for [x (xml-seq 
	 (parse (java.io.File. "data/sequences/compositions.xml")))
      :when (= :composition (:tag x))]
  (:composer (:attrs x)))
; END:xml-seq
)




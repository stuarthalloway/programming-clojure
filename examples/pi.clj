(ns examples.pi)

(defn in-circle? [[x y]]
  (<= (Math/sqrt (+ (* x x) (* y y))) 1))

; there is no great math behind this, but at least the seeds are different...
(def seed (ref (System/currentTimeMillis)))
(defn next-seed [] (dosync (alter seed inc)))

(def *random* nil)

(defn tl-rand 
  ([] (.nextDouble *random*))
  ([n] (* n (.nextDouble *random*))))

; you can try this one to see that thread-local rand 
; (above) is not the limiting factor
;(defn waste-time []
;  (set! *random* (reduce + (take 100 (iterate inc 1)))))
  
;(defn tl-rand
;  ([] (waste-time) 0.5)
;  ([n] (* n (tl-rand))))

(defn random-point []
  (let [random-coord (fn [] (dec (tl-rand 2)))]
    [(random-coord) (random-coord)]))
                   
(defstruct sample-results :in-circle :total)
(def default-sample-count 10000)

(defmulti run-simulation (fn [& args] (into [] (map class args))))

(defmethod run-simulation [Number]   
  [n] (run-simulation (struct sample-results 0 0) n))

(defmethod run-simulation [java.util.Map]
  [results] (run-simulation results default-sample-count))

(defmethod run-simulation [java.util.Map Number]
  [results n] 
  (println "Running on " (Thread/currentThread))
  (binding [*random* (java.util.Random. (next-seed))]
    (reduce (fn [{in-circle :in-circle total :total} point]
	      (struct sample-results 
		      (if (in-circle? point) (inc in-circle) in-circle)
		      (inc total)))
	    results
	    (take n (repeatedly random-point)))))

(defmulti guess-pi class)

(defmethod guess-pi java.util.Map [results]
  (/ (* 4.0 (:in-circle results)) (:total results)))

(defmethod guess-pi Number [n]
  (guess-pi (run-simulation n)))

; START: pguess-pi
(defn parallel-guess-pi [agent-count trials]
  (let [trials (quot trials agent-count)
	agents (for [_ (range agent-count)] (agent trials))]
    (doseq [a agents] (send a run-simulation))
    (apply await agents)
    (guess-pi (apply merge-with + (map deref agents)))))
; END: pguess-pi


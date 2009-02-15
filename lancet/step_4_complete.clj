(ns lancet.step-4-complete
  (:use [clojure.contrib.except :only (throw-if)]
	lancet.step-2-complete lancet.step-3-complete))

(defmacro has-run? [f]
  `((:has-run-fn (meta (var ~f)))))

(defmacro reset [f]
  `((:reset-fn (meta (var ~f)))))

(defmacro deftarget [sym doc & forms]
  (let [has-run-fn (gensym "hr-") reset-fn (gensym "rf-")]
    `(let [[~has-run-fn ~reset-fn once-fn#] (runonce (fn [] ~@forms))]
       (def ~(with-meta 
	      sym 
	      {:doc doc :has-run-fn has-run-fn :reset-fn reset-fn}) 
	    once-fn#))))

(defmacro define-ant-task [clj-name ant-name]
  `(defn ~clj-name [props#]
     (let [task# 
	   (instantiate-task ant-project ~(name ant-name) props#)]
       (.execute task#)
       task#)))

(defn task-names [] 
  (map symbol (sort (.. ant-project getTaskDefinitions keySet))))

(defn safe-ant-name [n]
  (if (resolve n) (symbol (str "ant-" n)) n))

(defmacro define-all-ant-tasks []
  `(do ~@(map (fn [n] `(define-ant-task ~(safe-ant-name n) ~n)) 
	      (task-names))))

(define-all-ant-tasks)




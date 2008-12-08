(ns lancet.step-4-complete
  (:use [clojure.contrib.except :only (throw-if)]
	lancet.step-2-complete lancet.step-3-complete))

(defmacro has-run? [f]
  `((:has-run (meta (var ~f)))))

(defmacro reset [f]
  `((:reset-fn (meta (var ~f)))))

(defmacro defrunonce [sym doc & forms]
  (let [has-run (gensym "hr-") reset-fn (gensym "rf-")]
    `(let [[~has-run ~reset-fn once-fn#] (runonce (fn [] ~@forms))]
       (def ~(with-meta sym {:doc doc :has-run has-run :reset-fn reset-fn}) 
	    once-fn#))))

(defmacro deftarget 
  [sym & forms]
  (if (string? (first forms))
    `(defrunonce ~sym ~(first forms) [] ~@(rest forms))
    `(defrunonce ~sym "a lancet target" [] ~@forms)))

(defmacro define-ant-task [task-name]
  `(defn ~task-name [props#]
     (let [task# (instantiate-task ant-project ~(name task-name) props#)]
       (.execute task#))))

(defn task-names [] (seq (.. ant-project getTaskDefinitions keySet)))

(def colliding-names
  '#{import touch sync concat filter replace get apply})

(defn safe-task-names []
  (map 
   (fn [n] (if (get colliding-names (symbol n))
	     (str "ant-" n)
	     n))
   (task-names)))

(defmacro define-all-ant-tasks []
  `(do ~@(map (fn [n] `(define-ant-task ~(symbol n))) (task-names))))

(defmacro define-all-ant-tasks []
  `(do ~@(map (fn [n] `(define-ant-task ~(symbol n))) (safe-task-names))))

(define-all-ant-tasks)



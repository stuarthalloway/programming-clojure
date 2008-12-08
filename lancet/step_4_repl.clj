(ns lancet.step-4-repl
    (:use lancet.step-2-complete lancet.step-3-complete))

; START: has-run-fn
(defn has-run? [v]
  ((:has-run (meta v))))
; END: has-run-fn

(def has-run-fn has-run?)

; START: has-run-macro
(defmacro has-run? [f]
  `((:has-run (meta (var ~f)))))
; END: has-run-macro

; START: reset
(defmacro reset [f]
  `((:reset-fn (meta (var ~f)))))
; END: reset

; START: defrunonce
(defmacro defrunonce [sym doc & forms]
  (let [has-run (gensym "hr-") reset-fn (gensym "rf-")]
    `(let [[~has-run ~reset-fn once-fn#] (runonce (fn [] ~@forms))]
       (def ~(with-meta sym {:doc doc :has-run has-run :reset-fn reset-fn}) 
	    once-fn#))))
; END: defrunonce

; START: deftarget
(defmacro deftarget 
  [sym & forms]
  (if (string? (first forms))
    `(defrunonce ~sym ~(first forms) [] ~@(rest forms))
    `(defrunonce ~sym "a lancet target" [] ~@forms)))
; END: deftarget

; START: define-ant-task
(defmacro define-ant-task [task-name]
  `(defn ~task-name [props#]
     (let [task# (instantiate-task ant-project ~(name task-name) props#)]
       (.execute task#))))
; END: define-ant-task

; START: task-names
(defn task-names [] (seq (.. ant-project getTaskDefinitions keySet)))
; END: task-names

; START: safe-task-names
(def colliding-names
  '#{import touch sync concat filter replace get apply})

(defn safe-task-names []
  (map 
   (fn [n] (if (get colliding-names (symbol n))
	     (str "ant-" n)
	     n))
   (task-names)))
; END: safe-task-names

; START: define-all-ant-tasks
(defmacro define-all-ant-tasks []
  `(do ~@(map (fn [n] `(define-ant-task ~(symbol n))) (task-names))))
; END: define-all-ant-tasks

; START: safe-define-all-ant-tasks
(defmacro define-all-ant-tasks []
  `(do ~@(map (fn [n] `(define-ant-task ~(symbol n))) (safe-task-names))))
; END: safe-define-all-ant-tasks

(define-all-ant-tasks)



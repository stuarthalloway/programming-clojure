(ns examples.test.tasklist
  (:use clojure.test)
  (:require examples.tasklist))

(deftest tasklist-main
  (is (=
       "[init compile-java compile-clojure clojure jar all clean]\n"
       (with-out-str (examples.tasklist/-main "data/snippets/example-build.xml"))))
)

(deftest tasklist-compilation
  (compile 'examples.tasklist)
)


  

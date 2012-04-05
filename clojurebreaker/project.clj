; START: clojurebreaker-project
(defproject clojurebreaker "0.1.0-SNAPSHOT"
            :description "Clojurebreaker game for Programming Clojure 2nd Edition"
            :dependencies [[org.clojure/clojure "1.3.0"]
                           [org.clojure/math.combinatorics "0.0.1"]
                           [org.clojure/test.generative "0.1.3"]
                           [noir "1.2.0"]]
            :main clojurebreaker.server)
; END: clojurebreaker-project

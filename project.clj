(defproject programming-clojure "1.2.0"
  :description "Examples from Programming Clojure"
  :dependencies [[org.clojure/clojure
                  "1.2.0-master-SNAPSHOT"]
                 [org.clojure/clojure-contrib
                  "1.2.0-master-SNAPSHOT"]
                 [compojure
                  "0.3.2"]
                 [jline
                  "0.9.94"]
                 [hsqldb
                  "1.8.0.10"]]
  :dev-dependencies [[autodoc "0.7.0"]
                     [swank-clojure "1.1.0"]]
  :repositories {"clojure-releases" "http://build.clojure.org/releases"
                 "sesame" "http://repo.aduna-software.org/maven2/releases"})

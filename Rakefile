# here is a task that is not (yet) easy to write in Clojure...
desc "Build latest Clojure, Clojure-Contrib, and Compojure"
task :book_deps do
  Dir.chdir ENV["CLOJURE_HOME"] do
    system "ant jar"
  end
  Dir.chdir ENV["CLOJURE_CONTRIB_HOME"] do
    system "ant"
  end
  Dir.chdir ENV["COMPOJURE_HOME"] do
    system "ant"
  end
  cp "#{ENV['CLOJURE_HOME']}/clojure.jar", "lib/" 
  cp "#{ENV['CLOJURE_CONTRIB_HOME']}/clojure-contrib.jar", "lib/" 
  cp "#{ENV['COMPOJURE_HOME']}/compojure.jar", "lib/" 
end

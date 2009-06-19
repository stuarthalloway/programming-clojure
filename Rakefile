# here is a task that is not (yet) easy to write in Clojure...
task :build_compojure do
  Dir.chdir ENV["COMPOJURE_HOME"] do
    system "git pull"
    system "ant clean jar" # Clojure compiler changes may necessitate a clean
  end
end

task :build_clojure do
  Dir.chdir ENV["CLOJURE_HOME"] do
    system "git pull"
    system "ant jar"
  end
end

task :build_clojure_contrib do
  Dir.chdir ENV["CLOJURE_CONTRIB_HOME"] do
    system "git pull"
    system "ant -Dclojure.jar=../clojure/clojure.jar clean jar" # Clojure compiler changes may necessitate a clean
  end
end

desc "Get Clojure, Clojure-Contrib, and Compojure from Compojure"
task :deps_from_compojure => [:build_compojure] do
  cp "#{ENV['COMPOJURE_HOME']}/deps/clojure.jar", "lib/" 
  cp "#{ENV['COMPOJURE_HOME']}/deps/clojure-contrib.jar", "lib/" 
  cp "#{ENV['COMPOJURE_HOME']}/compojure.jar", "lib/" 
end

desc "Get all deps from their own projects"
task :deps => [:build_clojure, :build_clojure_contrib, :build_compojure] do
  cp "#{ENV['CLOJURE_HOME']}/clojure.jar", "lib/" 
  cp "#{ENV['CLOJURE_CONTRIB_HOME']}/clojure-contrib.jar", "lib/" 
  cp "#{ENV['COMPOJURE_HOME']}/compojure.jar", "lib/" 
end

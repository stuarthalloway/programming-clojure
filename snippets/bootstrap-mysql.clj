(defn bootstrap-mysql
  ([] 
     (bootstrap-mysql "file:///Users/stuart/devtools/java/mysql-connector-java-5.0.6/mysql-connector-java-5.0.6-bin.jar"))
  ([jar] 
     (add-classpath jar)
     (java.sql.DriverManager/registerDriver (.newInstance (Class/forName "com.mysql.jdbc.Driver")))))


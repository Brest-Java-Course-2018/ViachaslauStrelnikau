# ViachaslauStrelnikau test epam project
[![Build Status](https://travis-ci.org/Brest-Java-Course-2018/ViachaslauStrelnikau.svg?branch=master)](https://travis-ci.org/Brest-Java-Course-2018/ViachaslauStrelnikau)

[![Coverage Status](https://coveralls.io/repos/github/Brest-Java-Course-2018/ViachaslauStrelnikau/badge.svg)](https://coveralls.io/github/Brest-Java-Course-2018/ViachaslauStrelnikau)

####1. Check  
    
    $java -version  
    
    $export JAVA_HOME = ...
    
    $mvn -version
    
####2. Build

    
    $mvn clean install
    
####3. Preparing repotrs
  
    $mvn site
  
    $mvn site:stage
  
    check: ``<project>/target/stage/index.html``
    
####4.  Travis CI integration 

https://travis-ci.org/Brest-Java-Course-2018/ViachaslauStrelnikau/

####5. Use embedded jetty server for REST application test 

    mvn -pl rest/ jetty:run
        
    Once started, the REST server should be available at:
        
    http://localhost:8088
        
    Try CURL:
        
    curl -v localhost:8088/groups
        
    curl -v localhost:8088/groups/1
        
    curl -H "Content-Type: application/json" -X POST -d '{"groupId":1,"shortName":"A12","fullName":"Architecture 12","groupAvgMarks":6.75}' -v localhost:8088/groups/1
        
    curl -X "DELETE" localhost:8088/groups/1
    
####6.Use embedded jetty server for WEB application test 

   Set variable PORT, in rest.properties in rest-client module, equal 8088
   
   Set variable App, in rest.properties in rest-client module, to empty value
   
   mvn -pl rest-client/ clean install
   
   mvn -pl web-app/ clean install
   
   mvn -pl web-app/ jetty:run

   Once started, the application should be available at:

   http://localhost:8080

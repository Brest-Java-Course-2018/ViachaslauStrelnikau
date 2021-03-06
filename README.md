# ViachaslauStrelnikau test epam project
###Spring MVC + thymeleaf/BootStrap 4+EXTjs 6+ Angular 7 project

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
   
   #EXTJS
   
####1.Install ruby
    ubuntu:
    sudo apt-get install ruby-full
   
####2. download Sencha cmd
    ubuntu:
    http://cdn.sencha.com/cmd/6.6.0.13/no-jre/SenchaCmd-6.6.0.13-linux-amd64.sh.zip
   
####3. unzip archive

####4. install Sencha Cmd from unziped file

####5. reboot

####6. download extJS GPL SDK and unpack it

    https://ext4all.com/ext/download/ext-6.2.0-gpl.zip  
    run: sencha config --prop {path-to-sdk}\ext-6.2.0 --save
   
####6. go to EXTjs application folder

####7. run: sencha workspace init

####8. goto Extjs/BizDash folder of an application

####9. run sencha app watch

####10. App will be available at http://localhost:1841/BizDash

####11. App is expecting server at localhost:8088


   #Reactive Mongo
    
###1
# ViachaslauStrelnikau test epam project
[![Build Status](https://travis-ci.org/Brest-Java-Course-2018/ViachaslauStrelnikau.svg?branch=master)](https://travis-ci.org/Brest-Java-Course-2018/ViachaslauStrelnikau)

[![Coverage Status](https://coveralls.io/repos/github/Brest-Java-Course-2018/ViachaslauStrelnikau/badge.svg)](https://coveralls.io/github/Brest-Java-Course-2018/ViachaslauStrelnikau)

1. Check  
    
    $java -version  
    
    $export JAVA_HOME = ...
    
    $mvn -version
    
2. Build

    
    $mvn clean install
    
3. Preparing repotrs
  
    $mvn site
  
    $mvn site:stage
  
    check: ``<project>/target/stage/index.html``
4.  Travis CI integration https://travis-ci.org/Brest-Java-Course-2018/ViachaslauStrelnikau/
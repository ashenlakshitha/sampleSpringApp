
 Introduction
=============================================
    This contains information regarding build and run the project in linux environment..


 Technologies
=============================================
    java        
    Gradle
    JPA
    Git
    sonar
    Liquibase
    jasmine    
    Spring Security
    Spring MVC REST + Jackson
    Spring Data JPA + Bean Validation
    Spring Boot
    HTML5 Boilerplate
    Twitter Bootstrap 
    Thymeleaf
    jQuery
    Ehcache
    Log management with Logback
    Connection pooling with HikariCP


 Prerequisites
=============
[see Configuration section for help if any of following is missing]

 1.java 

 2.mysql [if installed,create a new user called intertrav with password as "password". Create an empty database "intertrav"]
 create user - CREATE USER 'newuser'@'localhost' IDENTIFIED BY 'password';GRANT ALL PRIVILEGES ON * . * TO 'newuser'@'localhost';
 creaet databse - drop database intertrav; create database intertrav default character set utf8;

 3.Install maven
    sudo apt-get install maven2
 
 4.Install oracle jar in to your local repository
    mvn install:install-file -Dfile=path_to_ojdbc.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=12.0 -Dpackaging=jar
 
 5.Oracle [Required if you run in production mode, see configuration section for help]
   
 6.Recommended IDE: intellij Idea

 
 setup guide
==================
 
1.check out the project from git server

       create new project form intellij Idea (file-> new-> projectFromVersionControl-> Git)
       Git Repository url : http://172.31.17.199:8880/intervest/intersure.git
       OR
       create a local git repository (git init <my local repo>) and clone the project.  
       git clone http://172.31.17.199:8880/intervest/intersure.git
       Then import to intellij Idea.
 
 
 Configuration
==================

 1.Git

        1. install git from Ubuntu Software Center
        2. save credentials
           git config credential.helper store
        3. set your name and emil
           git config --global user.name "Your Full Name"
           git config --global user.email "your_email_address@intervest.gi"
           
    Reference for useful commands - http://orga.cat/posts/most-useful-git-commands 

     
2.java
 
      1.sudo apt-get install <required jdk> (1.7 or above version and Oracle jdk is prefer)
      2.And set environmental variables
           JAVA_HOME=/usr/lib/jvm/<installed jdk>
           PATH=$PATH:$JAVA_HOME/bin
           export PATH

3.mysql

      1.sudo apt-get install mysql-server
      2.set the root password as "password"
        Once you install mysql, execute mysql_secure_installation
        
        If you have a different password which you really need to keep as it is, please change application-dev.yml with correct MySql password.
                Note: PLEASE DO NOT COMMIT application-dev.yml FILE WITH DATABASE PASSWORD CHANGE. If you want to skip committing it ignore
                the file using command, git update-index --assume-unchanged application-dev.yml        
                [When you want to start keeping track again, git update-index --no-assume-unchanged application-dev.yml]
                
      3.create an empty database "intertrav"
        use following commands:
        1. mysql -u root -p
        2. create database intertrav default character set utf8;
        3. show databases;   (Now you will see 'intertrav' database in the database list) 

4.JDK

      Add following jars into {JAVA_HOME}/jre/lib/security to enable AES 256 bit key encryption. This will be used if you are working with memorable word.
      Refer requirement document to find what is memorable word.
      Existing files should be replaced with new JARs
        1. local_policy.jar
        2. US_export_policy.jar
        (These jars can be downloaded  for java 7.from  http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html)

        
5.Oracle
    
    You do not really need oracle to set up your development environment. It will work with mysql. But if required you can work application with Oracle.
    Production mode will work with Oracle by default where Dev mode will work with mysql by default.
    
    Oracle database should be created by DBA because we need to set tablespaces and other permissions. If you want to drop created tables you can run the following task.
    Please note that you will need to include clean task if you change the properties in the gradle.properties file. eg. changing tablespaces parameter
    
    ./gradlew clean dropTables
    
    If you want to get the the PL/SQL statement refer the sql file mentioned in the gradle task.
    
    If you change the password, then change application-prod.yml with correct password.       
    Note: PLEASE DO NOT COMMIT application-prod.yml FILE WITH DATABASE PASSWORD CHANGE. If you want to skip committing it ignore
    the file using command, git update-index --assume-unchanged application-prod.yml        
    [When you want to start keeping track again, git update-index --no-assume-unchanged application-prod.yml]
    
    
 Build project
==================

    Refer respective module's README.
        myaccount-web   -   Public web site for customers
        myaccount-hub   -   Call center web site for agents. This is not publicly available.
        myaccount-api   -   Api module which will be used in both web and hub
        messaging-api   -   Contains all email related save, send operations
        triggering-api  -   Mainly contains scheduler to trigger events
   
 
 Common build errors
==================
   
       1. If any issue with missing jar ojdbc6-12.0.jar,  manually put ojdbc6-12.0.jar into maven repository

Load initial Data
=================

    To create myaccount users:
    
    dev mode (mysql)
        ./gradlew initData 
        
    production (oracle)
        ./gradlew initData -P prod
        you can specify which oracle db to update by setting relevant properties in gradle.properties(root) file
        
        
Version updating
=================

    To update version, run this command:
        sh versionUpdate.sh
    (this shellscript will take the new version as a console input and update the 'version.properties' file
     and make a git commit for that version update)


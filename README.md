                             # README #
# Gestion_Etudiants
  this small demo project helps to manage students database, it states a bunch of mandatory functions to manage students like registration and listing students.  

 to develop this project i used Java, Jee,Spring boot,jpa and thymleaf wich is modern server-side Java template engine for both web and standalone environments, and MysQL as a database engine. 
 
## Prerequisites
1. install java 8

2. install maven: 3.5.3

3. we use here Mysql as a TYPE of DB. so in order to run your the app you have to install mysql (we used the version 4.8.0). or MAMP/XAMP server, wich will provide you Mysql and Apache server.

## Before Running 

**all the configurations are in src/main/resources/application.properties at the moment and you can put it wherever you want.
 
** Change the informations about your Database (the user, password, port ...) in the    "src/main/resources/application.properties"

** You will not have to create any Database,it will be created automatically with name:EtudiantDB .

** there is a folder named External-resources it containes the photos and registration files of students, every photo added will be added will be persisted there, it will be better to put it outside the project, i put it there just for the domo.

## Running the app
** you can then run these two commands using terminal in the root of your project:

   cd  /path-to-directory
   
   mvn spring-boot:run
 
 ### ###
or if you prefer Eclipse you can easily run the api by clicking on the project then choose Run as Spring boot App.
 AND CHECK THE URL http://localhost:8089/Etudiant/Index in your favorite Browser.
 
 
## Built With
* [Java](http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html)
* [Spring](https://spring.io/docs) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Thymleaf](https://www.thymeleaf.org/) - modern server-side Java template engine

## Versioning
* 1.0

## Who do I talk to?
* bouichou67@gmail.com

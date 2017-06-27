# Sapphire-API
A Level-2 Rest API(Java Apache Jersey) for the Appworks Sapphire project. The API handles the CRUD operations of the Angular UI.

# Required Dependencies :
```
1. Maven
2. Java 8
3. Servlet Container (Tomcat/Jboss).
4. Mongo DB
```

# Loding Data / Seeding Data to Mongo DB.
Import data to Mongo DB from the data folder of the project.
```
command: mongorestore --db appdb --drop

ex : mongorestore --db appdb --drop ..\appworks\database\appdb\
```

It is a maven java web application project. To build the project run the command from the location of *pom file*
```
mvn clean install
```

After successfully building the project. The war file can be found in the **target directory**.

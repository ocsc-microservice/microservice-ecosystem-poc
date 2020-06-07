# microservice-ecosystem-poc
This repository is used for microservice POC 

1) Install MYSQL Database 
2) Provide username - root & password - admin 
3) Create new database with name - ocsc_poc
4) enter the command - use ocsc_poc;
5) Run sql scripts on mysql database. 

STEPS to Run Code : - 
1) Checkout the master branch 
2)  mvn clean install the parent pom.xml
3) Run the services in the following order : - 

1) Discovery-service 
2) Api-Gateway
3) Any Service 

How to access 
==============
One can access the REST API through Gateway Port hostname & port  

http://localhost:8765/api/<service-name>/<path>
  
For example : -   
http://localhost:8765/api/user-service/user/

Will add more during the final build phase 

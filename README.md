I am using Oracle DB

To change the properties of the DB please change the application.properties for DB url, username, password <sample below>

#Oracle
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:orcl1
spring.datasource.username=test
spring.datasource.password=test123$

spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver
spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.Oracle10gDialect

#`hibernate_sequence' doesn't exist
spring.jpa.hibernate.use-new-id-generator-mappings=false

# drop n create table, good for testing, comment this in production
spring.jpa.hibernate.ddl-auto=create
spring.jpa.properties.hibernate.format_sql=true

====================================================================================

====================================================================================
Steps to RUN

1) To Run the you can start the program from eclipse
   Right click the BankingApplication.java and run as JavaApplication
   
   OR
   
   Assuming you have your java, maven path set goto the project directory (banking)
   
   $mvn clean package spring-boot:repackage 
   $java -jar target/banking-0.0.1-SNAPSHOT.jar
   
=====================================================================================
Open Postman or any other rest client

Settings : Set the Body and JSON options after selecting POST 
           Provide Basic Auth with username = test and password = test123

User Story 1 : Deposit Money
url : localhost:8080/banking/deposit (POST Request)
param : {
			"accountNumber":"12345",
			"amount":"50"
			}
			
			
User Story 2 : Withdraw Money
url : localhost:8080/banking/withdraw (POST Request)
param : {
			"accountNumber":"12345",
			"amount":"50"
		}
		
User Story 3 : Account Balance
url : localhost:8080/banking/getBalance (POST Request)
param : {
			"accountNumber":"12345"
		}
		
User Story 4 : Get Transactions
url : localhost:8080/banking/getTransactions (POST Request)
param : {
			"accountNumber":"12345"
		}
		
============================================================================================
For Security : Basic Authentication is provided with username=test and password=test123

For Persistance: Spring JPA

For CI/CD - Jenkins Pipeline can be used
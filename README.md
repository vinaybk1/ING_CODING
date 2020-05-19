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
Table Structure (create query)

CREATE TABLE "TEST"."TRANSACTION" 
   (	"TRANSACTION_ID" NUMBER(19,0) NOT NULL PRIMARY KEY, 
	"ACCOUNT_NUMBER" NUMBER(19,0), 
	"AMOUNT" FLOAT(126), 
	"BALANCE" FLOAT(126), 
	"STATUS" VARCHAR2(255 CHAR), 
	"TYPE" VARCHAR2(255 CHAR));

====================================================================================
RUN
====

1) To Run the you can start the program from eclipse
   Right click the BankingApplication.java and run as JavaApplication
   
   OR
   
   Assuming you have your java, maven path set goto the project directory (banking)
   
   $mvn clean package spring-boot:repackage 
   $java -jar target/banking-0.0.1-SNAPSHOT.jar
   
=====================================================================================
Open Postman or any other rest client

User Story 1 : Deposit Money
url : localhost:8080/banking/deposit (POST Request)
param : {
			"accountNumber":"12345",
			"amount":"50"
			}
			
			
User Story 1 : Withdraw Money
url : localhost:8080/banking/deposit (POST Request)
param : {
			"accountNumber":"12345",
			"amount":"50"
		}
		
User Story 1 : Account Balance
url : localhost:8080/banking/deposit (POST Request)
param : {
			"accountNumber":"12345"
		}
		
User Story 1 : Get Transactions
url : localhost:8080/banking/deposit (POST Request)
param : {
			"accountNumber":"12345"
		}

Currency converter

The application is designed to convert currencies.
The source of exchange rates is the website of the European Central Bank.
The username and password allows a user to login to account.
Before first login user should create an account (email as username, password should be at least 6 characters).
At application start up, the test user will be created (user@gmail.com "qqqqqq").
After login user will be redirected to the main page, where he can choose currency and amount to convert.
Currency conversion occurs after click on the button "Convert".

Before currency conversion carried out a check of the relevance of rates.
If new rates are found, it will be saved in to the database.
The application stores the history of currency conversions

The history is available on the same page by clicking on the button "Get my history".
The updated history after performing new conversion is available on the same page by clicking on the button "Update my history ".
Removing unnecessary entries is available by clicking on the button "Trash"
Logout is available by clicking on the button "Logout"


Requirements
Java 11
Maven
Spring boot 2.3.2
PostgresSQL 14

To create a database:
(Create database named "calculator", username "postgres" password "kalevlaev")

Commands for building the project:
mvn compile
mvn package
To add the project JAR file to the local repository:
mvn install

Run project without using IDE:
mvn spring-boot: run

Currency converter
===

## The application is designed to convert currencies.
The source of exchange rates is the website of the European Central Bank.
Username and password allows the user to log into the account.
Before logging for the first time, the user must create an account (email address as username, password must be at least 6 characters long).
A test user will be created when the application is launched for the first time (user@gmail.com "qqqqqq").
After logging, the user will be redirected to the main page, where he can select the currency and amount to convert.
Currency conversion occurs after clicking on the "Convert" button.

Before converting currencies, the relevance of the exchange rate is checked.
If new rates are found, it will be saved in to the database.
The application stores the history of currency conversions.

The history is available on the same page by clicking on the "Get my history" button.
The updated history after performing a new conversion is available on the same page by clicking on the "Update my history" button.
Removing unnecessary entries is available by clicking on the "Trash" button.
Logout is available by clicking on the "Logout" button.


## Requirements:
##### Java 11
#### Maven
#### PostgresSQL 14

## To create a database:
Create database named "calculator", username "postgres" password "kalevlaev"

## Commands for building the project:
###### mvn compile
###### mvn package
#### To add the project JAR file to the local repository:
###### mvn install

## Run project without using IDE:
###### mvn spring-boot: run

## The application is running on localhost
###### http://localhost:8080/

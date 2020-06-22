# financial-analytics
Trading and analytics application using Spring Boot and Vaadin.

## Table of contents
* [General info](#general-info)
* [Screenshots](#screenshots)
* [Built with](#built-with)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)
* [Contact](#contact)

## General info

The purpose of this application is to enable forex and stock market trading training and to present analyzes and predictions.
I program this application to learn full-stack development. I was a trader before, so the idea came to me naturally.

## Screenshots
![Example screenshot](./img/img1.png)

## Built with
* Java 8 
* Spring Boot
* Vaadin
* Gradle

## Setup
#### Basic setup:
* Clone this repository 
* Check financial-analytics\src\main\resources\application.properties and create database and user with logging details specified in the file.
* Build with gradle
* load database/database.sql
* Clone this repository https://github.com/Luke1024/tradingAppFrontEnd and build it with gradle.
* Run financial-analytics\src\main\java\com\finance\FintechApplication.java
* Run tradingAppFrontEnd\src\main\java\frontend\VaadinApplication.java
* Open http://localhost:8080/ in a browser.

#### How to run data parser:
* Download csv files compatible with MetaTrader 4 for example here: https://www.histdata.com/download-free-forex-data/
* Save files in financial-analytics\src\main\resources\data
* Set path in financial-analytics\src\main\java\com\finance\preprocessor\HistoricDataLoader.java
* Set name of currencypair in CurrencyFile
* extractCurrencyPairs method in HistoricDataLoader.java is set to extract H1 datapoints from M1 csv file.
* Run application and open link http://localhost:8081/finance/admin/load

## Features
#### Features working:
* drawing currency pair charts on main page
* parsing csv files compatible with metatrader4

#### Planned features:
* trading on forex and stock market
* simple analysis with basic indicators
* analysis with neural network trained in keras
* chat between users
* order tracking between user

## Status
#### Working:
* main page drawing charts
#### To do soon:
* optimize chart drawing
* implement caching of data for faster retrieving
* enable real time data update
* add login and sign with sending email 
* build user dashboard enabling trading
* build email notifier when order hit stoploss or takeprofit

## Contact
chajdas.lukasz@gmail.com
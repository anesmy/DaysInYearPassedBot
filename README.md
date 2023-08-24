# DaysInYearPassedBot

Welcome to the DaysInYearPassedBot project! This is a Telegram bot that provides information on how many days have passed in the current year, presented as a percentage.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Running with Docker Compose](#running-with-docker-compose)
- [Configuration](#configuration)
- [Usage](#usage)
- [Contributing](#contributing)

## Introduction

The DaysInYearPassedBot is a Telegram bot built using the Spring Boot. It fetches and sends updates to users, providing information about the percentage of days that have already passed in the current year. 
Users can interact with the bot by sending specific commands.

## Features

- Send daily updates to users with the percentage of days passed.
- Users can start and stop receiving updates by sending the `/start` and `/stop` commands.
- Configurable bot settings through the `application.properties` file.

## Prerequisites

Before you begin, ensure you have met the following requirements:
- Java Development Kit (JDK) 17 or higher
- Maven for building the project
- PostgreSQL database (You can use a Docker container for development)
- A Telegram bot token (create one on [Telegram's BotFather](https://core.telegram.org/bots#botfather))

## Getting Started

1. Clone this repository:
   `git clone https://github.com/anesmy/DaysInYearPassedBot.git`
2. Navigate to the project directory:
   `cd DaysInYearPassedBot`
3. Create a PostgreSQL database and configure the database connection in `src/main/resources/application.properties`.
4. Build the project using Maven:
   `mvn clean install`
5. Run the application:
   `java -jar target/DaysInYearPassedBot-1.0.jar`
6. Start chatting with your Telegram bot!

## Running with Docker Compose

This project includes a Docker Compose configuration to simplify running the application and its required services.

1. Open a terminal window.
2. Navigate to the project directory containing the `docker-compose.yml` file.
3. Run the following command to start the application and associated services:
   `docker-compose up`

This will create and run containers for your application.

## Configuration

You need to configure the application through the `application.properties` file. Provide your bot's token and database connection details:

Bot Configuration
   
  `bot.name=YourBotName` 
  `bot.token=YourBotToken` 

Database Configuration
   
  `spring.datasource.url=jdbc:postgresql://your-database-url/your-database-name` 
  `spring.datasource.username=your-database-username` 
  `spring.datasource.password=your-database-password` 

## Usage

To interact with the bot, open your Telegram app and search for your bot by its name. Send the following commands:
- /start: Start receiving daily updates.
- /stop: Stop receiving updates.

## Contributing

Contributions are welcome! Please feel free to open issues or submit pull requests to improve this project.

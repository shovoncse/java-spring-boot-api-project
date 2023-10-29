# Guess the Number Game Design Document [@Shovan Das](https://github.com/shovoncse)

## Introduction

The "Guess the Number" game is a Java-based application that allows players to guess a random number within a specified range. This design document outlines the architecture, Java classes, and their interactions within the application.

![uml](https://github.com/shovoncse/java-spring-boot-api-project/blob/main/documentation/uml.png?raw=true)

## Architecture

The application follows a simple client-server architecture where the server hosts the game logic, and clients can interact with the game via HTTP requests. The game consists of players, games, and game moves.

## Java Classes

### Player

- **Description**: Represents a player in the game.
- **Methods**:
  - `getName()`: Get the player's name.
  - `setName(name)`: Set the player's name.
  - `getGamesPlayed()`: Get the number of games played by the player.
  - `getTotalMoves()`: Get the total moves made by the player.
  - `incrementGamesPlayed()`: Increment the number of games played by the player.
  - `incrementTotalMoves(moves)`: Increment the total moves made by the player.

### PlayerImpl

- **Description**: An implementation of the `Player` interface.
- **Methods**: Inherits methods from the `Player` interface.

### GuessTheNumberGame

- **Description**: Represents the guessing game.
- **Fields**:
  - `maxAttempts`: The maximum number of attempts allowed.
  - `targetNumber`: The random number to be guessed.
  - `currentPlayerGuess`: The current guess made by the player.
  - `currentPlayer`: The current player.
  - `gameWon`: A flag to indicate if the game has been won.
  - `totalMoves`: The total number of moves made in the game.

- **Methods**:
  - `startNewGame()`: Start a new game.
  - `makeGuess(guess)`: Make a guess in the game.
  - `isGameOver()`: Check if the game is over.
  - `getWinner()`: Get the winner of the game.
  - `getGuessResult(guess)`: Get the result of a guess (e.g., "The number is larger," "The number is smaller").

### PlayerRepository

- **Description**: Manages players and their data.
- **Fields**:
  - `players`: A map of player names to player objects.
  - `fileName`: The name of the file to save player data.

- **Methods**:
  - `createPlayer(name)`: Create a new player.
  - `getPlayer(name)`: Get a player by name.
  - `savePlayersToFile()`: Save player data to a text file.

### GameController

- **Description**: Handles HTTP requests and game interactions.
- **Fields**:
  - `playerRepository`: An instance of the `PlayerRepository` class.
  - `game`: An instance of the `GuessTheNumberGame` class.

- **Endpoints**:
  - `/player`: Create a player and get player information.
  - `/game`: Create a game and make moves in the game.

## Class Interactions

- The `Player` and `PlayerImpl` classes handle player-related data and statistics.
- The `GuessTheNumberGame` class manages the game logic, including starting new games, making guesses, and determining game outcomes.
- The `PlayerRepository` class manages player data and file I/O.
- The `GameController` class handles HTTP requests, interacts with the `PlayerRepository`, and controls game flow.

## Conclusion

This design document provides an overview of the Java classes and their interactions within the "Guess the Number" game application. The system allows players to participate in the game, track their statistics, and make guesses to determine the winner.

![Placeholder Image](placeholder_image.png)

For more detailed information on class methods and functionality, please refer to the corresponding Java source code.


API documentation for my guessing game application:

# Guess the Number Game API [@Shovan Das](https://github.com/shovoncse)

The Guess the Number Game API allows you to create players, start new games, and make guesses in a guessing game. Players can track their statistics, including the number of games played and total moves made.

## Table of Contents

- [Guess the Number Game Design Document @Shovan Das](#guess-the-number-game-design-document-shovan-das)
  - [Introduction](#introduction)
  - [Architecture](#architecture)
  - [Java Classes](#java-classes)
    - [Player](#player)
    - [PlayerImpl](#playerimpl)
    - [GuessTheNumberGame](#guessthenumbergame)
    - [PlayerRepository](#playerrepository)
    - [GameController](#gamecontroller)
  - [Class Interactions](#class-interactions)
  - [Conclusion](#conclusion)
- [Guess the Number Game API @Shovan Das](#guess-the-number-game-api-shovan-das)
  - [Table of Contents](#table-of-contents)
  - [Endpoints](#endpoints)
  - [Player](#player-1)
    - [Create a Player](#create-a-player)
    - [Get Player Information](#get-player-information)
  - [Game](#game)
    - [Create a Game](#create-a-game)
    - [Make a Move in the Game](#make-a-move-in-the-game)

## Endpoints

Base URL: `http://localhost:8080`

## Player

### Create a Player

- **URL:** `/player`
- **Method:** POST
- **Description:** Create a new player with a unique name.
- **Parameters:**
  - `name` (string, required) - The name of the player.
- **Example:**
  ```bash
  curl -X POST http://localhost:8080/player?name=Shovan
  ```
- **Response:**
  - HTTP Status 200 (OK) if the player is created successfully.
  - HTTP Status 400 (Bad Request) if a player with the same name already exists.

### Get Player Information

- **URL:** `/player`
- **Method:** GET
- **Description:** Retrieve player information, including their name, games played, and total moves.
- **Parameters:**
  - `name` (string, required) - The name of the player.
- **Example:**
  ```bash
  curl -X GET http://localhost:8080/player?name=Shovan
  ```
- **Response:**
  - HTTP Status 200 (OK) if the player exists.
  - HTTP Status 404 (Not Found) if the player is not found.
  - JSON Response:
  ```json
  {
    "name": "Shovan",
    "gamesPlayed": 1,
    "totalMoves": 3
  }
  ```

## Game

### Create a Game

- **URL:** `/game`
- **Method:** POST
- **Description:** Start a new guessing game.
- **Example:**
  ```bash
  curl -X POST http://localhost:8080/game
  ```
- **Response:**
  - HTTP Status 200 (OK) if a new game is started.
  - HTTP Status 400 (Bad Request) if a game is already in progress.

### Make a Move in the Game

- **URL:** `/game`
- **Method:** PUT
- **Description:** Make a move in the guessing game.
- **Parameters:**
  - `move` (integer, required) - The player's guess.
- **Example:**
  ```bash
  curl -X PUT http://localhost:8080/game?move=5
  ```
- **Response:**
  - HTTP Status 200 (OK) with response strings based on the guess:
    - "The number is larger"
    - "The number is smaller"
    - "Correct! You used X moves"
  - HTTP Status 400 (Bad Request) for invalid moves or game-related errors.

That's the API documentation for my Guess the Number Game. Users can create players, start games, and make guesses while keeping track of their game statistics.
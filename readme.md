API documentation for my guessing game application:

# Guess the Number Game API

The Guess the Number Game API allows you to create players, start new games, and make guesses in a guessing game. Players can track their statistics, including the number of games played and total moves made.

## Table of Contents

- [Guess the Number Game API](#guess-the-number-game-api)
  - [Table of Contents](#table-of-contents)
  - [Endpoints](#endpoints)
  - [Player](#player)
    - [Create a Player](#create-a-player)
    - [Get Player Information](#get-player-information)
  - [Game](#game)
    - [Create a Game](#create-a-game)
    - [Make a Move in the Game](#make-a-move-in-the-game)

## Endpoints

Base URL: `http://your-api-url.com`

## Player

### Create a Player

- **URL:** `/player`
- **Method:** POST
- **Description:** Create a new player with a unique name.
- **Parameters:**
  - `name` (string, required) - The name of the player.
- **Example:**
  ```bash
  curl -X POST http://your-api-url.com/player?name=Teemu
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
  curl -X GET http://your-api-url.com/player?name=Teemu
  ```
- **Response:**
  - HTTP Status 200 (OK) if the player exists.
  - HTTP Status 404 (Not Found) if the player is not found.
  - JSON Response:
  ```json
  {
    "name": "Teemu",
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
  curl -X POST http://your-api-url.com/game
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
  curl -X PUT http://your-api-url.com/game?move=5
  ```
- **Response:**
  - HTTP Status 200 (OK) with response strings based on the guess:
    - "The number is larger"
    - "The number is smaller"
    - "Correct! You used X moves"
  - HTTP Status 400 (Bad Request) for invalid moves or game-related errors.

That's the API documentation for my Guess the Number Game. Users can create players, start games, and make guesses while keeping track of their game statistics.
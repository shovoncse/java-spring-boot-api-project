package com.game.game;

public class GuessTheNumberGame extends Game {
    private int maxAttempts;
    private int currentPlayerGuess;
    private Player currentPlayer;
    private int totalMoves;
    public GuessTheNumberGame(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    @Override
    public void startNewGame() {
        targetNumber = (int) (Math.random() * 100); // Generate a random number between 0 and 99
        gameWon = false;
        currentPlayerGuess = -1;
    }

    @Override
    public boolean makeGuess(int guess) {
        if (isGameOver()) {
            return false;
        }

        currentPlayerGuess = guess;
        totalMoves++;

        if (guess == targetNumber) {
            gameWon = true;
        }

        return true;
    }

    public int getTotalMoves() {
        return totalMoves;
    }

    public String getGuessResult(int guess) {
        if (guess < targetNumber) {
            return "The number is larger";
        } else {
            return "The number is smaller";
        }
    }

    @Override
    public boolean isGameOver() {
        return gameWon || (currentPlayerGuess != -1 && maxAttempts <= 0);
    }

    @Override
    public String getWinner() {
        if (gameWon) {
            return currentPlayer.getName();
        } else {
            return "No winner";
        }
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }
}

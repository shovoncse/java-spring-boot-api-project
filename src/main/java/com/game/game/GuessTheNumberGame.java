package com.game.game;

import java.util.ArrayList;
import java.util.List;

public class GuessTheNumberGame extends Game {
    private int maxAttempts;
    private int targetNumber;
    private int currentPlayerGuess;
    private Player currentPlayer;
    private List<String> gameMoves;
    private int totalMoves;

    public GuessTheNumberGame(int maxAttempts) {
        this.maxAttempts = maxAttempts;
        this.gameMoves = new ArrayList<>();
    }

    @Override
    public void startNewGame() {
        targetNumber = (int) (Math.random() * 100);
        gameWon = false;
        currentPlayerGuess = -1;
    }

    public void assignPlayer(Player player) {
        this.currentPlayer = player;
    }

    public List<String> getGameMoves() {
        return gameMoves;
    }

    
    @Override
    public boolean makeGuess(int guess) {
        if (isGameOver()) {
            return false;
        }

        currentPlayerGuess = guess;

        String moveResult;
        if (guess == targetNumber) {
            gameWon = true;
            moveResult = "Correct! You used " + (gameMoves.size() + 1) + " moves";
        } else if (guess < targetNumber) {
            moveResult = "The number is larger";
        } else {
            moveResult = "The number is smaller";
        }

        gameMoves.add(currentPlayer.getName() + " guessed " + guess + " - " + moveResult);
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

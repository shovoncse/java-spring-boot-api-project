package com.game.game;

public abstract class Game {
    protected int targetNumber;
    protected boolean gameWon;

    public abstract void startNewGame();

    public abstract boolean makeGuess(int guess);

    public abstract boolean isGameOver();

    public abstract String getWinner();
}

package com.game.game;

public interface Player {
    String getName();
    void setName(String name);
    int getGamesPlayed();
    int getTotalMoves();
    void incrementGamesPlayed(); // Add this method
    void incrementTotalMoves(int moves); // Add this method
    // Add any other player-related methods here
}

package com.game.game;

public interface Player {
    String getName();
    void setName(String name);
    int getGamesPlayed();
    int getTotalMoves();
    void incrementGamesPlayed();
    void incrementTotalMoves(int moves);
}

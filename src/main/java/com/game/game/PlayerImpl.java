package com.game.game;

public class PlayerImpl implements Player {
    private String name;
    private int gamesPlayed;
    private int totalMoves;

    public PlayerImpl(String name) {
        this.name = name;
        this.gamesPlayed = 0;
        this.totalMoves = 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    @Override
    public int getTotalMoves() {
        return totalMoves;
    }

    @Override
    public void incrementGamesPlayed() {
        gamesPlayed++;
    }

    @Override
    public void incrementTotalMoves(int moves) {
        totalMoves += moves;
    }
}

package com.game.game;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;


@Repository
public class PlayerRepository {
    private Map<String, Player> players = new HashMap<>();
    private String fileName;

    public PlayerRepository() {
        fileName = "players.txt";
        
    }
    
    public Player createPlayer(String name) {
        if (players.containsKey(name)) {
            return null;
        }
        Player player = new PlayerImpl(name);
        players.put(name, player);
        savePlayersToFile();
        return player;
    }

    public Player getPlayer(String name) {
        return players.get(name);
    }

    private void savePlayersToFile() {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Player player : players.values()) {
                writer.write(player.getName() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

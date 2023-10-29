package com.game.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GameController {

    @Autowired
    private PlayerRepository playerRepository;
    private GuessTheNumberGame game;

    @PostMapping("/player")
    public ResponseEntity<Object> createPlayer(@RequestParam String name) {
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Player name is required.");
        }

        Player player = playerRepository.createPlayer(name);
        if (player == null) {
            return ResponseEntity.badRequest().body("Player with the same name already exists.");
        }

        return ResponseEntity.ok(player);
    }

    @GetMapping("/player")
    public ResponseEntity<Object> getPlayerInfo(@RequestParam String name) {
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Player name is required.");
        }

        Player player = playerRepository.getPlayer(name);
        if (player == null) {
            return ResponseEntity.ok("Player not found.");
        }

        return ResponseEntity.ok(player);
    }

    @PostMapping("/game")
    public ResponseEntity<Object> createGame(@RequestParam String playerName) {
        if (game != null && !game.isGameOver()) {
            return ResponseEntity.badRequest().body("A game is already in progress.");
        }

        if (playerName == null || playerName.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Player name is required to start a game.");
        }

        Player player = playerRepository.getPlayer(playerName);
        if (player == null) {
            return ResponseEntity.ok("Player not found.");
        }

        game = new GuessTheNumberGame(10); // Max attempts: 10
        game.assignPlayer(player);
        game.startNewGame();

        return ResponseEntity.ok("New game started.");
    }

    @PutMapping("/game")
    public ResponseEntity<Object> playGame(@RequestParam int move) {
        if (game == null) {
            return ResponseEntity.badRequest().body("No game in progress. Create a game first.");
        }

        if (game.isGameOver()) {
            return ResponseEntity.badRequest().body("Game is already over.");
        }

        Player currentPlayer = game.getCurrentPlayer();
        if (currentPlayer == null) {
            return ResponseEntity.badRequest().body("No player in the game.");
        }

        if (move < 0 || move > 99) {
            return ResponseEntity.badRequest().body("Invalid move. Guess a number between 0 and 99.");
        }

        boolean validMove = game.makeGuess(move);
        if (validMove) {
            if (game.isGameOver()) {
                int totalMoves = game.getTotalMoves();
                String result = "Correct! You used " + totalMoves + " moves";
                currentPlayer.incrementGamesPlayed();
                currentPlayer.incrementTotalMoves(totalMoves);
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.ok(game.getGuessResult(move));
            }
        } else {
            return ResponseEntity.badRequest().body("Invalid move. Guess a different number.");
        }
    }
}

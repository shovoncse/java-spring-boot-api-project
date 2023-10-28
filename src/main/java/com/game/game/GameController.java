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
    public ResponseEntity<String> createPlayer(@RequestParam String name) {
        Player player = playerRepository.createPlayer(name);
        if (player == null) {
            return ResponseEntity.badRequest().body("Player with the same name already exists.");
        }
        return ResponseEntity.ok("Player created: " + player.getName());
    }

    @GetMapping("/player")
    public ResponseEntity<String> getPlayerInfo(@RequestParam String name) {
        Player player = playerRepository.getPlayer(name);
        if (player == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Player: " + player.getName());
    }

    @PostMapping("/game")
    public ResponseEntity<String> createGame() {
        if (game == null || game.isGameOver()) {
            game = new GuessTheNumberGame(10); // Max attempts: 10
            Player currentPlayer = playerRepository.getPlayer("Player1");
            game.setCurrentPlayer(currentPlayer);
            game.startNewGame();
            return ResponseEntity.ok("New game started.");
        } else {
            return ResponseEntity.badRequest().body("A game is already in progress.");
        }
    }

    @PutMapping("/game")
    public ResponseEntity<String> playGame(@RequestParam int move) {
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
            return ResponseEntity.badRequest().body("Invalid move.");
        }
    }
}

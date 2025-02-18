package com.wipro.springboot.usecase1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    private final Map<String, Integer> activeGames = new HashMap<>();
    private final Map<String, Integer> guessCounts = new HashMap<>();  // Track guess attempts
    private static final Logger logger = LoggerFactory.getLogger(GameController.class); // Logger
    @PostMapping("/start")
    public String startGame(@RequestParam String playerName) {
        if (playerName == null || playerName.trim().isEmpty()) {
            return "Player name cannot be empty!";
        }

        if (activeGames.containsKey(playerName)) {
            return "Game already started for " + playerName + ". Please finish the current game!";
        }

        int numberToGuess = gameService.generateRandomNumber();
        activeGames.put(playerName, numberToGuess);
        guessCounts.put(playerName, 0);  // Initialize guess count
     // Log the generated number for debugging
        logger.info("Random number generated for {}: {}", playerName, numberToGuess);

        return "Game started for " + playerName + ". You have 3 attempts to guess!";
    }

    @PostMapping("/guess")
    public String guessNumber(@RequestParam String playerName, @RequestParam int guess) {
        if (!activeGames.containsKey(playerName)) {
            return "Start a new game first!";
        }

        int target = activeGames.get(playerName);
        int attempts = guessCounts.getOrDefault(playerName, 0) + 1;
        guessCounts.put(playerName, attempts);

        if (guess == target) {
            activeGames.remove(playerName);
            guessCounts.remove(playerName);
            gameService.updateBestScore(playerName, attempts, "won");
            return "Congratulations, " + playerName + "! You guessed the number correctly in " + attempts + " attempts.";
        } 
        
        if (attempts >= 3) {
            activeGames.remove(playerName);
            guessCounts.remove(playerName);
            gameService.updateBestScore(playerName, Integer.MAX_VALUE, "failed");
            return "Game over for " + playerName + ". You failed to guess correctly within 3 attempts.";
        }

        return guess < target ? "Too low! Attempts left: " + (3 - attempts) : "Too high! Attempts left: " + (3 - attempts);
    }
}

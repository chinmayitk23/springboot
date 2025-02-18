package com.wipro.springboot.usecase1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

@Service
public class GameService {

    @Autowired
    private PlayerRepository playerRepository;

    private final Random random = new Random();
    private static final Logger logger = LoggerFactory.getLogger(GameService.class);

    public int generateRandomNumber() {
        return random.nextInt(100) + 1;
    }

    public Player getOrCreatePlayer(String name) {
        logger.info("Looking for player: {}", name);
        return playerRepository.findByName(name)
                .orElseGet(() -> {
                    logger.info("Creating new player: {}", name);
                    Player newPlayer = new Player(name, Integer.MAX_VALUE, "failed"); // Default status is "failed"
                    playerRepository.save(newPlayer);
                    logger.info("Saved new player: {}", name);
                    return newPlayer;
                });
    }

    public void updateBestScore(String name, int score, String status) {
        Player player = getOrCreatePlayer(name);
        logger.info("Current best score for {}: {}", name, player.getBestScore());
        if (status.equals("won")) {
            if (score < player.getBestScore()) {
                player.setBestScore(score);
            }
        }
        player.setStatus(status); // Update the status (won or failed)
        playerRepository.save(player);
        logger.info("Updated player status for {}: {} and best score: {}", name, status, player.getBestScore());
    }
    public void setPlayerStatus(String name, String status) {
        Player player = getOrCreatePlayer(name);
        player.setStatus(status);
        playerRepository.save(player);
        logger.info("Updated player {} status to: {}", name, status);
    }

}

package com.wipro.springboot.usecase1;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int bestScore;
    private String status; // Track whether the player won or failed

    // Constructors
    public Player() {}

    public Player(String name, int bestScore, String status) {
        this.name = name;
        this.bestScore = bestScore;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {  // Add this method to set the status
        this.status = status;
    }
}

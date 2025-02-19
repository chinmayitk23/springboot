package com.wipro.springboot.usecase1;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByName(String name);
    List<Player> findAll(); // Retrieve all players
}

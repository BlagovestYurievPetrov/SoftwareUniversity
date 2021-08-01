package com.example.football.repository;

import com.example.football.models.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PlayerRepository  extends JpaRepository<Player,Long> {

    boolean existsByEmail(String email);

    //List<Player> findAllByBirthDateAfterAndBirthDateBefore(LocalDate birthDate, LocalDate birthDate2);

    @Query("SELECT p FROM Player p WHERE p.birthDate BETWEEN :birthDate AND :birthDate2 ORDER BY p.stats.shooting DESC, p.stats.passing DESC, p.stats.endurance DESC, p.lastName")
    List<Player> findAllByBirthDateOrderByStatsAndLastName(LocalDate birthDate, LocalDate birthDate2);
}

package com.task.cricketGame.repository;

import com.task.cricketGame.model.Innings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InningsRepository extends JpaRepository<Innings,Integer> {

    @Query("select u from Innings u where u.matches.matchId=:matchId and u.inningsNumber=1")
    Optional<Innings> findByMatchId(Integer matchId);
}

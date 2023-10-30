package com.task.cricketGame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.task.cricketGame.model.Matches;

import java.util.List;
import java.util.Map;

@Repository
public interface MatchesRepository extends JpaRepository<Matches, Integer>{

    @Query("select m.matchId as matchId, m.matchDate as matchDate, m.venue as venue,m.result as winner, m.resultDiscription as resultDiscription," +
            "m.team1Id.teamName as team1, m.team2Id.teamName as team2 " +
            "from Matches m where m.matchId=:matchId")
    Map<String, Object> findByMatchId(Integer matchId);
}

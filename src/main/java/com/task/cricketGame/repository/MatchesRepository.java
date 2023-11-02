package com.task.cricketGame.repository;

import com.task.cricketGame.model.BallByBall;
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

    @Query("select b.matches.team1Id.teamName as team1Name,b.matches.team2Id.teamName as team2Name,b.matches.result as result,b.matches.resultDiscription as resultDiscription,b.matches.matchType as matchType," +
            "b.matches.venue as venue,sum(b.runScored) as score,b.matches.matchDate as matchDate from BallByBall b where b.matches.matchId=:matchId and b.inningsNumber=:inningsNumber and b.runScored!=7")
    Map<String,Object> findMatchDetails(Integer matchId, Integer inningsNumber);
}

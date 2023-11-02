package com.task.cricketGame.repository;

import com.task.cricketGame.model.PlayerInnings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface PlayerInningsRepository extends JpaRepository<PlayerInnings,Integer> {

    @Query("select pl.runsScored as runsScored, p.playerName as playerName, p.role as role, p.teams.teamName as teamName, m.team1Id.teamName as team1, m.team2Id.teamName as team2,m.matchDate as matchDate" +
            " from PlayerInnings pl left join Players p on pl.player.playerId=p.playerId left join Matches m on pl.matches.matchId=m.matchId" +
            " where pl.matches.matchId=:matchId and pl.player.playerId=:playerId")
    Optional<Map<String,Object>> findByMatchId(Integer matchId,Integer playerId);
}

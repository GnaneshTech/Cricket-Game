package com.task.cricketGame.repository;

import com.task.cricketGame.model.BallByBall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface BallByBallRepository extends JpaRepository<BallByBall,Integer> {

    @Query("select sum(b.runScored) from BallByBall b where b.inningsNumber=:inningsNumber and b.matches.matchId=:matchId and b.batsmanId.playerId=:playerId")
    Integer findPlayerInningsForMatch(Integer inningsNumber, Integer matchId, Integer playerId);
}

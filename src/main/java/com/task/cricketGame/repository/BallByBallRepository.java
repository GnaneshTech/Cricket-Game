package com.task.cricketGame.repository;

import com.task.cricketGame.model.BallByBall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface BallByBallRepository extends JpaRepository<BallByBall,Integer> {

    @Query("select sum(b.runScored) from BallByBall b where b.inningsNumber=:inningsNumber and b.matches.matchId=:matchId and b.batsmanId.playerId=:playerId and b.runScored!=7")
    Integer findPlayerInningsForMatch(Integer inningsNumber, Integer matchId, Integer playerId);

    @Query("select b from BallByBall b where b.inningsNumber=:inningsNumber and b.matches.matchId=:matchId and b.batsmanId.playerId=:playerId and b.runScored=7")
    BallByBall findPlayerWicketInningsForMatch(Integer inningsNumber, Integer matchId, Integer playerId);
}

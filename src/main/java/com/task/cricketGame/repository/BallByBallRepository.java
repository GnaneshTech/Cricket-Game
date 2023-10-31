package com.task.cricketGame.repository;

import com.task.cricketGame.model.BallByBall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BallByBallRepository extends JpaRepository<BallByBall,Integer> {
}

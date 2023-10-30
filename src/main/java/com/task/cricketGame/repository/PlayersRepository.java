package com.task.cricketGame.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.task.cricketGame.model.Players;

@Repository
public interface PlayersRepository extends JpaRepository<Players, Integer>{
    Optional<Players> findByPlayerName(String playerName);

//	@Query("select p from Players p where p.teams.teamId=:teamId")
//	List<Players> findByTeamsId(Integer teamId);

}

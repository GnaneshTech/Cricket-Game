package com.task.cricketGame.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.cricketGame.model.Teams;

@Repository
public interface TeamsRepository extends JpaRepository<Teams, Integer>{

	Teams findByTeamName(String teamA);

}

package com.task.cricketGame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.cricketGame.model.Matches;

@Repository
public interface MatchesRepo extends JpaRepository<Matches, Integer>{

}

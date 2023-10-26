package com.task.cricketGame.service;

import org.springframework.http.ResponseEntity;

import com.task.cricketGame.Response;

public interface MatchService {
	
	public ResponseEntity<?> startMatch(String teamA, String teamB, Integer overs);
	
	public ResponseEntity<?> getAllTeams();

}

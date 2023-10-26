package com.task.cricketGame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.cricketGame.Response;
import com.task.cricketGame.model.MatchesDTO;
import com.task.cricketGame.service.MatchService;

@RestController
public class MatchController {
	
	@Autowired
	MatchService matchService;
	
	@PostMapping("/startMatch")
	public ResponseEntity<?> startMatch(@RequestBody MatchesDTO matchesDTO){
		try {
			return matchService.startMatch(matchesDTO.getTeamA(), matchesDTO.getTeamB(), matchesDTO.getOvers());
		}catch(Exception e) {
			e.printStackTrace();
			 return ResponseEntity.ok(new Response("Exception in Controller",e.getMessage()));
		}
	}

}

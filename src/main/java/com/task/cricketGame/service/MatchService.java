package com.task.cricketGame.service;

import com.task.cricketGame.WebModel.MatchesWebModel;
import org.springframework.http.ResponseEntity;

import com.task.cricketGame.Response;

public interface MatchService {
	
	public ResponseEntity<?> startMatch(MatchesWebModel matchesWebModel);

	public ResponseEntity<?> fetchPlayerDetails(MatchesWebModel matchesWebModel);

	public ResponseEntity<?> fetchMatchDetails(Integer matchId);

}

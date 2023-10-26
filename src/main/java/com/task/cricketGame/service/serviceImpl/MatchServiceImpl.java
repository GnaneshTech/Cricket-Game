package com.task.cricketGame.service.serviceImpl;

import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.task.cricketGame.Response;
import com.task.cricketGame.model.Matches;
import com.task.cricketGame.model.Teams;
import com.task.cricketGame.repository.MatchesRepo;
import com.task.cricketGame.repository.PlayersRepository;
import com.task.cricketGame.repository.TeamsRepository;
import com.task.cricketGame.service.MatchService;

@Service
public class MatchServiceImpl implements MatchService{
	
	@Autowired
	TeamsRepository teamsRepository;
	
	@Autowired
	PlayersRepository playersRepository;
	
	@Autowired
	MatchesRepo matchesRepo;

	@Override
	public ResponseEntity<?> startMatch(String teamA, String teamB, Integer overs) {
		HashMap<String, Object> response = new HashMap<>();
		String winningTeam = null;
		try {
			Teams team1 = teamsRepository.findByTeamName(teamA);
			Teams team2 = teamsRepository.findByTeamName(teamB);
			
			Random random = new Random();
			
			Teams firstInningsTeam;
			Teams secondInningsTeam;
			
			if(random.nextBoolean()) {
				firstInningsTeam=team1;
				secondInningsTeam=team2;
			}else {
				firstInningsTeam=team2;
				secondInningsTeam=team1;
			}
			Matches firstInnings = playFirstInnings(firstInningsTeam, overs);
			Matches secondInnings = playSecondInnings(secondInningsTeam, overs, firstInnings.getMatchId());
			
			if(firstInnings.getTeamAScore()>secondInnings.getTeamBScore()) {
				 response.put(firstInnings.getTeamA(), firstInnings);
				 winningTeam=firstInningsTeam.getTeamName();
			}else {
				response.put(secondInnings.getTeamB(), secondInnings);
				winningTeam=secondInningsTeam.getTeamName();
			}
			
			Optional<Matches> matchData = matchesRepo.findById(firstInnings.getMatchId());
			Matches match = matchData.get();
			match.setWinner(winningTeam);
			matchesRepo.save(match);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(new Response(winningTeam+" Won the match",response));
	}
	
	public Matches playFirstInnings(Teams battingTeam, Integer overs) {
		int totalScore = 0;
		int totalWickets = 0;
		
		int totalBalls = overs*6;
		for(int i=1;i<=totalBalls;i++) {
			if(totalWickets==10) {
				break;
			}
			
			Random random = new Random();
			int outCome = random.nextInt(8);
			if(outCome==7) {
				totalWickets++;
			}else {
				totalScore+=outCome;
			}
			
		}
		
		Matches match = new Matches();
		match.setTeamA(battingTeam.getTeamName());
		match.setTeamAScore(totalScore);
		match.setTeamAWickets(totalWickets);
		match = matchesRepo.save(match);
		
		return match;
		
	}
	
	public Matches playSecondInnings(Teams battingTeam, Integer overs,Integer matchId) {
		int totalScore = 0;
		int totalWickets = 0;
		
		int totalBalls = overs*6;
		for(int i=1;i<=totalBalls;i++) {
			if(totalWickets==10) {
				break;
			}
			
			Random random = new Random();
			int outCome = random.nextInt(8);
			if(outCome==7) {
				totalWickets++;
			}else {
				totalScore+=outCome;
			}
			
		}
		
		Optional<Matches> matchData = matchesRepo.findById(matchId);
		
		Matches match = matchData.get();
		match.setTeamB(battingTeam.getTeamName());
		match.setTeamBScore(totalScore);
		match.setTeamBWickets(totalWickets);
		match = matchesRepo.save(match);
		
		return match;
		
	}

	@Override
	public ResponseEntity<?> getAllTeams() {
		try {
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}

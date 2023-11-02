package com.task.cricketGame.service.serviceImpl;

import com.task.cricketGame.Response;
import com.task.cricketGame.WebModel.MatchesWebModel;
import com.task.cricketGame.model.*;
import com.task.cricketGame.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.task.cricketGame.service.MatchService;

import javax.jws.Oneway;
import javax.swing.text.html.Option;
import java.util.*;

@Service
public class MatchServiceImpl implements MatchService{
	
	@Autowired
	TeamsRepository teamsRepository;
	@Autowired
	PlayersRepository playersRepository;
	@Autowired
	MatchesRepository matchesRepo;
	@Autowired
	InningsRepository inningsRepository;
	@Autowired
	BallByBallRepository ballByBallRepository;
	@Autowired
	PlayerInningsRepository playerInningsRepository;

	private final static Logger logger = LoggerFactory.getLogger(MatchServiceImpl.class);

	@Override
	public ResponseEntity<?> startMatch(MatchesWebModel matchesWebModel) {
		HashMap<String, Object> response = new HashMap<>();
		StringBuilder str = new StringBuilder();
		try {
			Teams team1 = teamsRepository.findByTeamName(matchesWebModel.getTeam1()).get();
			Teams team2 = teamsRepository.findByTeamName(matchesWebModel.getTeam2()).get();

			Random random = new Random();

			if(random.nextBoolean()) {
				matchesWebModel.setFirstInningsTeam(team1);
				matchesWebModel.setSecondInningsTeam(team2);
			}else {
				matchesWebModel.setFirstInningsTeam(team2);
				matchesWebModel.setSecondInningsTeam(team1);
			}
			Matches firstInnings = playFirstInnings(matchesWebModel);
			matchesWebModel.setMatchId(firstInnings.getMatchId());
			Matches secondInnings = playSecondInnings(matchesWebModel);

			if(firstInnings.getMatchId().equals(secondInnings.getMatchId())) {
				Map<String, Object> matchData = matchesRepo.findByMatchId(secondInnings.getMatchId());
				response.put("matchDetails", matchData);
				str.append(secondInnings.getResult());
			}
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("startMatch Impl Exception",e.getMessage()));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new Response(str.toString()+" Won the match",response));
	}

	public Matches playFirstInnings(MatchesWebModel matchesWebModel) {
		int totalScore = 0;
		int totalWickets = 0;
		int totalOvers = matchesWebModel.getOvers();

		Matches match = new Matches();
		Teams team1 = teamsRepository.findById(matchesWebModel.getTeam1Id()).get();
		Teams team2 = teamsRepository.findById(matchesWebModel.getTeam2Id()).get();
		match.setTeam1Id(team1);
		match.setMatchDate(matchesWebModel.getMatchDate());
		match.setVenue(matchesWebModel.getVenue());
		match.setMatchType(matchesWebModel.getMatchType());
		match = matchesRepo.save(match);

		List<Players> battingPlayers = playersRepository.findByTeamId(team1.getTeamId());
		int battingPlayerCount = 0;


		for(int over=1;over<=totalOvers;over++){
			for(int i=1;i<=6;i++) {
				if(totalWickets==10) {
					break;
				}
				Random random = new Random();
				int outCome = random.nextInt(8);
				if(outCome==7) {
					totalWickets++;
					BallByBall ball = new BallByBall();
					ball.setMatches(match);
					ball.setOverNumber(over);
					ball.setBatsmanId(battingPlayers.get(battingPlayerCount));
					ball.setInningsNumber(1);
					ball.setBallNumber(i);
					ball.setRunScored(7);
					ballByBallRepository.save(ball);
					Integer playerScore = ballByBallRepository.findPlayerInningsForMatch(ball.getInningsNumber(),ball.getMatches().getMatchId(),ball.getBatsmanId().getPlayerId());
					PlayerInnings playerInnings = new PlayerInnings();
					playerInnings.setMatches(match);
					playerInnings.setPlayer(ball.getBatsmanId());
					playerInnings.setRunsScored(playerScore);
					playerInningsRepository.save(playerInnings);
					battingPlayerCount++;
				}else {
					totalScore+=outCome;
					BallByBall ball = new BallByBall();
					ball.setMatches(match);
					ball.setOverNumber(over);
					ball.setInningsNumber(1);
					ball.setBatsmanId(battingPlayers.get(battingPlayerCount));
					ball.setBallNumber(i);
					ball.setRunScored(outCome);
					ballByBallRepository.save(ball);
				}
			}
		}
		Innings innings = new Innings();
		innings.setMatches(match);
		innings.setBattingTeamId(team1);
		innings.setBowlingTeamId(team2);
		innings.setInningsNumber(1);
		innings.setTotalRuns(totalScore);
		innings.setTarget(totalScore+1);
		innings=inningsRepository.save(innings);



		return match;

	}

	public Matches playSecondInnings(MatchesWebModel matchesWebModel) {
		int totalScore = 0;
		int totalWickets = 0;

		int totalOvers = matchesWebModel.getOvers();

		Optional<Matches> matchData = matchesRepo.findById(matchesWebModel.getMatchId());
		Matches match = matchData.get();
		Teams team1 = teamsRepository.findById(matchesWebModel.getTeam2Id()).get();
		Teams team2 = teamsRepository.findById(matchesWebModel.getTeam1Id()).get();
		match.setTeam2Id(team1);

		List<Players> battingPlayers = playersRepository.findByTeamId(team1.getTeamId());
		int battingPlayerCount = 0;

		for(int over=1;over<=totalOvers;over++){
			for(int i=1;i<=6;i++) {
				if(totalWickets==10) {
					break;
				}
				Random random = new Random();
				int outCome = random.nextInt(8);
				if(outCome==7) {
					totalWickets++;
					BallByBall ball = new BallByBall();
					ball.setMatches(match);
					ball.setOverNumber(over);
					ball.setInningsNumber(2);
					ball.setBatsmanId(battingPlayers.get(battingPlayerCount));
					ball.setBallNumber(i);
					ball.setRunScored(7);
					ballByBallRepository.save(ball);
					Integer playerScore = ballByBallRepository.findPlayerInningsForMatch(ball.getInningsNumber(),ball.getMatches().getMatchId(),ball.getBatsmanId().getPlayerId());
					PlayerInnings playerInnings = new PlayerInnings();
					playerInnings.setMatches(match);
					playerInnings.setPlayer(ball.getBatsmanId());
					playerInnings.setRunsScored(playerScore);
					playerInningsRepository.save(playerInnings);
					battingPlayerCount++;
				}else {
					totalScore+=outCome;
					BallByBall ball = new BallByBall();
					ball.setMatches(match);
					ball.setOverNumber(over);
					ball.setInningsNumber(2);
					ball.setBatsmanId(battingPlayers.get(battingPlayerCount));
					ball.setBallNumber(i);
					ball.setRunScored(outCome);
					ballByBallRepository.save(ball);
				}
			}
		}

		Innings innings = new Innings();
		innings.setMatches(match);
		innings.setBattingTeamId(team1);
		innings.setBowlingTeamId(team2);
		innings.setInningsNumber(2);
		innings.setTotalRuns(totalScore);
		innings= inningsRepository.save(innings);

		Optional<Innings> inningsData = inningsRepository.findByMatchId(match.getMatchId());
		if(inningsData.get().getTarget()>innings.getTotalRuns()){
			match.setResult(matchData.get().getTeam1Id().getTeamName());
			match.setResultDiscription(match.getTeam2Id().getTeamName()+" beaten by "+matchData.get().getTeam1Id().getTeamName());
		} else if (inningsData.get().getTarget()<innings.getTotalRuns()) {
			match.setResult(match.getTeam2Id().getTeamName());
			match.setResultDiscription(matchData.get().getTeam1Id().getTeamName()+" beaten by "+match.getTeam2Id().getTeamName());
		} else if (inningsData.get().getTarget().equals(innings.getTotalRuns())) {
			match.setResult("DRAW");
			match.setResultDiscription("Both team scored "+innings.getTotalRuns());
		}
		match = matchesRepo.save(match);
		return match;

	}


	@Override
	public ResponseEntity<?> fetchPlayerDetails(MatchesWebModel matchesWebModel) {
		HashMap<String,Object> response = new HashMap<>();
		try{
			Optional<Map<String,Object>> playerInnings = playerInningsRepository.findByMatchId(matchesWebModel.getMatchId(),matchesWebModel.getPlayerId());
			if (playerInnings.isPresent()){
				HashMap<String,Object> playerInningsData = new HashMap<>();
				playerInningsData.put("runsScored",playerInnings.get().get("runsScored"));
				playerInningsData.put("playerName",playerInnings.get().get("playerName"));
				playerInningsData.put("role",playerInnings.get().get("role"));
				playerInningsData.put("teamName",playerInnings.get().get("teamName"));
				playerInningsData.put("match",playerInnings.get().get("team1")+" Vs "+playerInnings.get().get("team2"));
				playerInningsData.put("matchDate",playerInnings.get().get("matchDate"));
				response.put("playerInningsDetails",playerInningsData);
			}else{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("teamId or playerId not found",""));
			}
		}catch (Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("fetchPlayerDetails Impl Exception",e.getMessage()));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new Response("fetchPlayerInnings Success",response));
	}

	@Override
	public ResponseEntity<?> fetchMatchDetails(Integer matchId) {
		HashMap<String,Object> response = new HashMap<>();
		try{
			Map<String,Object> team1 = matchesRepo.findMatchDetails(matchId,1);
			Map<String,Object> team2 = matchesRepo.findMatchDetails(matchId,2);
			HashMap<String, Object> matchDetails = new HashMap<>();
			matchDetails.put("match",team1.get("team1Name")+" Vs "+team2.get("team2Name"));
			matchDetails.put("winner",team2.get("result"));
			matchDetails.put("resultDiscription",team2.get("resultDiscription"));
			matchDetails.put("matchType",team2.get("matchType"));
			matchDetails.put("venue",team2.get("venue"));
			matchDetails.put(team1.get("team1Name")+"Score",team1.get("score"));
			matchDetails.put(team2.get("team2Name")+"Score",team2.get("score"));
			matchDetails.put("team1",team1.get("team1Name"));
			matchDetails.put("team2",team2.get("team2Name"));
			matchDetails.put("matchDate",team2.get("matchDate"));
			response.put("matchDetails",matchDetails);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("fetchMatchDetails Impl Exception",e.getMessage()));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new Response("fetchMatchDetails Success",response));
	}


}

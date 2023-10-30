package com.task.cricketGame.service.serviceImpl;

import com.task.cricketGame.Response;
import com.task.cricketGame.WebModel.PlayersWebModel;
import com.task.cricketGame.model.Players;
import com.task.cricketGame.model.Teams;
import com.task.cricketGame.repository.PlayersRepository;
import com.task.cricketGame.repository.TeamsRepository;
import com.task.cricketGame.service.PlayersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayersService {
    @Autowired
    PlayersRepository playersRepository;
    @Autowired
    TeamsRepository teamsRepository;

    private final static Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);
    @Override
    public ResponseEntity<?> addPlayer(PlayersWebModel playersWebModel) {
        HashMap<String,Object> response = new HashMap<>();
        try{
            Optional<Players> playerData = playersRepository.findByPlayerName(playersWebModel.getPlayerName());
            if(!playerData.isPresent()){
                Players players = new Players();
                players.setPlayerName(playersWebModel.getPlayerName());
                players.setDob(playersWebModel.getDob());
                Optional<Teams> teamData = teamsRepository.findById(playersWebModel.getTeamId());
                players.setTeams(teamData.get());
                players.setRole(playersWebModel.getRole());
                players = playersRepository.save(players);
                logger.info(players.getPlayerName()+" player added successfully");
                response.put("player",players);
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(playersWebModel.getPlayerName()+" player already exists",""));
            }
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("addPlayer serviceImpl Exception",e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Response(playersWebModel.getPlayerName()+" player added successfully",response));
    }

    @Override
    public ResponseEntity<?> getAllPlayers() {
        HashMap<String,Object> response = new HashMap<>();
        try{
            List<Players> players = playersRepository.findAll();
            response.put("players",players);
        }catch (Exception e){
            e.printStackTrace();
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("getAllPlayer Impl Exception",e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Response("getAllPlayer Success",response));
    }
}

package com.task.cricketGame.service.serviceImpl;

import com.task.cricketGame.Response;
import com.task.cricketGame.WebModel.TeamsWebModel;
import com.task.cricketGame.model.Teams;
import com.task.cricketGame.repository.TeamsRepository;
import com.task.cricketGame.service.TeamsService;
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
public class TeamsServiceImpl implements TeamsService {

    @Autowired
    TeamsRepository teamsRepository;

    private final static Logger logger = LoggerFactory.getLogger(TeamsServiceImpl.class);
    @Override
    public ResponseEntity<?> addTeams(TeamsWebModel teamsWebModel) {
        HashMap<String,Object> response = new HashMap<>();
        try{
            Optional<Teams> teamData = teamsRepository.findByTeamName(teamsWebModel.getTeamName());
            if(!teamData.isPresent()){
                Teams teams = new Teams();
                teams.setTeamName(teamsWebModel.getTeamName());
                teams.setNationality(teamsWebModel.getNationality());
                teams = teamsRepository.save(teams);
                logger.info(teams.getTeamName()+" team added successfully");
                response.put("team",teams);
            }else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(teamsWebModel.getTeamName() + " team is already exists", ""));
            }
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("addTeams Impl Exception",e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Response("Team added Successful",response));
    }

    @Override
    public ResponseEntity<?> getAllTeams() {
        HashMap<String,Object> response = new HashMap<>();
        try{
            List<Teams> teams = teamsRepository.findAll();
            response.put("teams",teams);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("getAllTeams Impl Exception",e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Response("getAllPlayers Successful",response));
    }
}

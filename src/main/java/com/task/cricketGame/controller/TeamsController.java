package com.task.cricketGame.controller;

import com.task.cricketGame.Response;
import com.task.cricketGame.WebModel.PlayersWebModel;
import com.task.cricketGame.WebModel.TeamsWebModel;
import com.task.cricketGame.service.TeamsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teams")
public class TeamsController {

    @Autowired
    TeamsService teamsService;


    @PostMapping("/addTeams")
    public ResponseEntity<?> addTeams(@RequestBody TeamsWebModel teamsWebModel){
        try{
            return teamsService.addTeams(teamsWebModel);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("addTeams controller Exception",""));
        }
    }

    @GetMapping("/getTeams")
    public ResponseEntity<?> getTeams(){
        try{
            return teamsService.getAllTeams();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("getTeams controller Exception",""));
        }
    }
}

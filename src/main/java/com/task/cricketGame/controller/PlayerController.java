package com.task.cricketGame.controller;

import com.task.cricketGame.Response;
import com.task.cricketGame.WebModel.PlayersWebModel;
import com.task.cricketGame.service.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/player")
public class PlayerController {
    @Autowired
    PlayersService playersService;


    @PostMapping("/add")
    public ResponseEntity<?> addPlayer(@RequestBody PlayersWebModel playersWebModel){
        try{
            return playersService.addPlayer(playersWebModel);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("addPlayer controller Exception",""));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllPlayer(){
        try{
            return playersService.getAllPlayers();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("addPlayer controller Exception",""));
        }
    }
}

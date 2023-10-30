package com.task.cricketGame.service;

import com.task.cricketGame.WebModel.PlayersWebModel;
import org.springframework.http.ResponseEntity;

public interface PlayersService {

    public ResponseEntity<?> addPlayer(PlayersWebModel playersWebModel);

    public ResponseEntity<?> getAllPlayers();

}

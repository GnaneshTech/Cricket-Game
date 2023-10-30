package com.task.cricketGame.service;

import com.task.cricketGame.WebModel.PlayersWebModel;
import com.task.cricketGame.WebModel.TeamsWebModel;
import org.springframework.http.ResponseEntity;

public interface TeamsService {

    public ResponseEntity<?> addTeams(TeamsWebModel teamsWebModel);

    public ResponseEntity<?> getAllTeams();
}

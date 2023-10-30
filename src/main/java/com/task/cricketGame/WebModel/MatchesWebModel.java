package com.task.cricketGame.WebModel;

import com.task.cricketGame.model.Teams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchesWebModel {

    private Integer matchId;
    private Date matchDate;
    private String venue;
    private Integer team1Id;
    private Integer team2Id;
    private String matchType;
    private String result;
    private String resultDiscription;
    private Integer team1Score;
    private Integer team2Score;
    private String team1;
    private String team2;
    private Integer overs;
    private Teams firstInningsTeam;
    private Teams secondInningsTeam;


}

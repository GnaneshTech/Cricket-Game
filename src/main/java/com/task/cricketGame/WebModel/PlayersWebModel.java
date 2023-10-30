package com.task.cricketGame.WebModel;

import com.task.cricketGame.model.Teams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayersWebModel {

    private Integer playerId;
    private String playerName;
    private Integer teamId;
    private Date dob;
    private String role;
}

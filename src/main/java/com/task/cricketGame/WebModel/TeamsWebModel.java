package com.task.cricketGame.WebModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamsWebModel {

    private Integer teamId;

    private String teamName;

    private String nationality;
}

package com.task.cricketGame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchesDTO {
	
	private String teamA;
	private String teamB;
	private Integer overs;

}

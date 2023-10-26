package com.task.cricketGame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="matches")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Matches {
	
	@Column	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer matchId;
	@Column
	private String teamA;
	@Column
	private String teamB;
	@Column
	private Integer teamAScore;
	@Column
	private Integer teamBScore;
	@Column
	private Integer teamAWickets;
	@Column
	private Integer teamBWickets;
	@Column
	private String winner;
	
	

}

package com.task.cricketGame.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

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
	private Date matchDate;

	@Column
	private String venue;

	@ManyToOne(cascade = CascadeType.ALL , optional = true)
	@JoinColumn(name="team1Id",referencedColumnName = "teamId")
	private Teams team1Id;

	@ManyToOne(cascade = CascadeType.ALL , optional = true)
	@JoinColumn(name="team2Id", referencedColumnName = "teamId")
	private Teams team2Id;

	@Column
	private String matchType;

	@Column
	private String result;

	@Column
	private String resultDiscription;

	@Column
	@CreationTimestamp
	private Date createdOn;

	@Column
	@CreationTimestamp
	private Date updatedOn;

	
	

}

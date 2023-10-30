package com.task.cricketGame.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name="players")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Players {
	
	@Column	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer playerId;
	@Column
	private String playerName;
	
	@ManyToOne(cascade = CascadeType.ALL , optional = true)
	@JoinColumn(name="teamId")
	private Teams teams;

	@Column
	private Date dob;

	@Column
	private String role;

	@Column
	@CreationTimestamp
	private Date createdOn;

	@Column
	@CreationTimestamp
	private Date updatedOn;

}

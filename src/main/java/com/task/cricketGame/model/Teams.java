package com.task.cricketGame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name="teams")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teams {
	
	@Column	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer teamId;
	@Column
	private String teamName;
	@Column
	private String nationality;
	@Column
	@CreationTimestamp
	private Date createdOn;
	@Column
	@CreationTimestamp
	private Date updatedOn;

}

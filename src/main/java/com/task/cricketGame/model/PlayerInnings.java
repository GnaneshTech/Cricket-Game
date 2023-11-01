package com.task.cricketGame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="player_innings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerInnings {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playerInningsId;

//    @ManyToOne(cascade = CascadeType.ALL, optional = false)
//    @JoinColumn(name = "inningsId")
//    private Innings inningsId;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "matchId", referencedColumnName = "matchId")
    private Matches matches;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "playerId", referencedColumnName = "playerId")
    private Players player;

    @Column
    private Integer runsScored;

    @Column
    @CreationTimestamp
    private Date createdOn;

    @Column
    @CreationTimestamp
    private Date updatedOn;





}

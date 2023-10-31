package com.task.cricketGame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.utility.nullability.MaybeNull;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ball_by_ball")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BallByBall {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ballId;

    @Column
    private Integer overNumber;

    @Column
    private Integer ballNumber;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "matchId", referencedColumnName = "matchId")
    private Matches matches;

    @Column
    private Integer inningsNumber;
//
//    @ManyToOne(cascade = CascadeType.ALL, optional = true)
//    @JoinColumn(name = "batsmanId", referencedColumnName = "playerId")
//    private Players batsmanId;

    @Column
    private Integer runScored;

    @Column
    @CreationTimestamp
    private Date createdOn;

    @Column
    @CreationTimestamp
    private Date updatedOn;
}

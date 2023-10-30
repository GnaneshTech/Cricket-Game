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

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "inningsId")
    private Innings inningsId;

    @Column
    private Integer overNumber;

    @Column
    private Integer ballNumber;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "bowlerId", referencedColumnName = "playerId")
    private Players bowlerId;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "batsmanId", referencedColumnName = "playerId")
    private Players batsmanId;

    @Column
    private Integer runScored;

    @Column
    @CreationTimestamp
    private Date createdOn;

    @Column
    @CreationTimestamp
    private Date updatedOn;
}

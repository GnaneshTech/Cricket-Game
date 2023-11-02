package com.task.cricketGame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="innings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Innings {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inningsId;

    @ManyToOne(cascade = CascadeType.ALL, optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name="matchId")
    private Matches matches;

    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name="battingTeamId",referencedColumnName = "teamId")
    private Teams battingTeamId;

    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name="bowlingTeamId",referencedColumnName = "teamId")
    private Teams bowlingTeamId;

    @Column
    private Integer inningsNumber;

    @Column
    private Integer totalRuns;

    @Column
    private Integer target;

    @Column
    @CreationTimestamp
    private Date createdOn;

    @Column
    @CreationTimestamp
    private Date updatedOn;

}

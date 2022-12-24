package com.ezrah.sitebackend.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * maps to KNS_Faction in api
 */
@Entity
@Table(name = "faction_per_knesset")
@Getter
@Setter
public class FactionPerKnesset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private Integer knsId;

    private LocalDateTime startDate;

    private LocalDateTime finishDate;

    private String name;

    private Integer knessetNum;

    @ManyToOne(fetch = FetchType.LAZY)
    private Faction faction;

    @OneToMany(mappedBy = "factionPersonWasIn")
    List<PersonPosition> peopleInPositions;
}

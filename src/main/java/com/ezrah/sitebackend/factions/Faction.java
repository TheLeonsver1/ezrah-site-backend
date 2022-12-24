package com.ezrah.sitebackend.factions;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "factions")
@Getter
@Setter
public class Faction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "faction")
    List<FactionPerKnesset> factionPerKnesset;

    @OneToMany(mappedBy = "duplicateOf")
    List<Faction> duplicates;

    @ManyToOne(fetch = FetchType.LAZY)
    Faction duplicateOf;

    @JoinTable(
            name = "faction_mergers",
            joinColumns = {@JoinColumn(name = "resulting_faction_id")},
            inverseJoinColumns = {@JoinColumn(name = "merged_faction_id")}
    )
    @ManyToMany
    List<Faction> mergerOf;

    @ManyToMany(mappedBy = "mergerOf")
    List<Faction> wasPartOfMerges;
}

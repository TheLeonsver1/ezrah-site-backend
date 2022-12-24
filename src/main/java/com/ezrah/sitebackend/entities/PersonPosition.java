package com.ezrah.sitebackend.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "person_to_position")
@Getter
@Setter
@ToString
public class PersonPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer knsId;

    Integer knsPersonId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    Person person;

    Integer knsPositionId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    Position position;

    Integer knessetNum;

    LocalDateTime startDate;

    LocalDateTime finishDate;

    Integer knsGovMinistryId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    Ministry ministryPersonWasIn;

    String dutyDescription;

    Integer knsFactionId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    FactionPerKnesset factionPersonWasIn;

    Integer knsCommitteeId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    Committee committeePersonWasIn;
}

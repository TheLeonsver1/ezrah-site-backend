package com.ezrah.sitebackend.committees;

import com.ezrah.sitebackend.persons.PersonPosition;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "commities")
@Getter
@Setter
public class Committee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer knsId;

    String name;

    Integer knessetNum;

    String email;

    LocalDateTime startDate;

    LocalDateTime finishDate;

    Integer knsParentCommitteeId;

    @OneToMany(mappedBy = "parentCommittee")
    List<Committee> childCommittees;

    @ManyToOne(fetch = FetchType.LAZY)
    Committee parentCommittee;

    Integer categoryID;

    String categoryDescription;

    Integer committeeTypeId;

    String committeeTypeDescription;

    Integer additionalTypeId;

    String additionalTypeDescription;

    @OneToMany(mappedBy = "committeePersonWasIn")
    List<PersonPosition> peopleInCommitteePositions;
}

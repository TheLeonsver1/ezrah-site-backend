package com.ezrah.sitebackend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Mapping of KNS_GovMinistry
 */
@Entity
@Table(name = "ministries")
@Getter
@Setter
public class Ministry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer knsId;

    String name;

    boolean isActive;

    LocalDateTime knsLastUpdatedDate;

    @OneToMany(mappedBy = "ministryInCharge")
    List<IsraeliLaw> lawsInChargeOf;

    @OneToMany(mappedBy = "ministryPersonWasIn")
    List<PersonPosition> personsInPositions;
}

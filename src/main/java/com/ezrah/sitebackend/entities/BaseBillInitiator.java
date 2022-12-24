package com.ezrah.sitebackend.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@ToString
public abstract class BaseBillInitiator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer knsId;

    Integer knsBillId;

    Integer knsPersonId;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    Bill bill;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    Person person;

    Boolean isInitiator;

    Integer ordinal;

    LocalDateTime knsLastUpdatedDate;
}

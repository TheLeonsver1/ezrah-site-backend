package com.ezrah.sitebackend.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "positions")
@Getter
@Setter
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(unique = true)
    Integer knsId;

    String description;

    LocalDateTime knsLastUpdatedDate;

    @OneToMany(mappedBy = "position")
    List<PersonPosition> peopleInPosition;

}
